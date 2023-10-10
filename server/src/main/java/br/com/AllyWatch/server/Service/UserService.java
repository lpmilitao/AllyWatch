package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.AddUserRequest;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.KeyRepository;
import br.com.AllyWatch.server.Repository.UserRepository;
import br.com.AllyWatch.server.Security.KeycloakUserCreation;
import br.com.AllyWatch.server.Validator.PasswordValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Optional;

import static br.com.AllyWatch.server.Core.Cryptography.*;
import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeyRepository keyRepository;

    @Transactional
    public void add(AddUserRequest request) {

        PasswordValidator.validate(request.getPassword());

        User newUser = User.builder().active(true).icon(NEUTRAL).build();

        KeyCrypt key = findKey();
        newUser.setCpf(
                encrypt(request.getCpf(), key.getPublicKey())
        );
        newUser.setFullname(
                encrypt(request.getFullname(), key.getPublicKey())
        );
        newUser.setEmail(
                encrypt(request.getEmail(), key.getPublicKey())
        );

        userRepository.save(newUser);

        try {
            KeycloakUserCreation.createUser(
                    newUser.getId(), request.getFullname(), request.getEmail(), request.getPassword()
            );
        } catch (Exception e){
            e.printStackTrace();

            userRepository.delete(newUser);
        }
    }

    public KeyCrypt findKey() {
        Optional<KeyCrypt> findKey = keyRepository.findAll().stream().findFirst();
        KeyCrypt key = null;

        if (findKey.isEmpty()) {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
                keyPairGenerator.initialize(KEY_SIZE);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                PrivateKey privateKey = keyPair.getPrivate();
                key = KeyCrypt.builder().privateKey(privateKey).publicKey(publicKey).build();
                keyRepository.save(key);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            key = findKey.get();
        }

        return key;
    }
}