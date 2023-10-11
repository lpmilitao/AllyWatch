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
import org.springframework.web.server.ResponseStatusException;

import java.security.*;
import java.util.Base64;
import java.util.Optional;

import static br.com.AllyWatch.server.Core.Cryptography.*;
import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

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

    private KeyCrypt findKey() {
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

    public User getAuthenticatedUser(String authorization){
        String email = getEmailFromJwtToken(authorization.substring(7));

        //Procurar usuário no banco pelo email, porém email no banco está encriptado;

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY));
    }

    private String getEmailFromJwtToken(String token){
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT token.");
        }

        String payload = parts[1];
        byte[] payloadBytes = Base64.getUrlDecoder().decode(payload);
        String payloadString = new String(payloadBytes);

        int start = payloadString.indexOf("\"email\":");
        String fromEmail = payloadString.substring(start + ("\"email\":\"").length());
        int end = fromEmail.indexOf("\"");

        return fromEmail.substring(0, end);
    }
}