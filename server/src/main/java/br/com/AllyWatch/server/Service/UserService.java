package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.IconRequest;
import br.com.AllyWatch.server.DTO.Request.UserRequest;
import br.com.AllyWatch.server.DTO.Response.UserResponse;
import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.UserRepository;
import br.com.AllyWatch.server.Security.KeycloakUserManagement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static br.com.AllyWatch.server.Domain.Enum.Icon.*;
import static br.com.AllyWatch.server.Security.Cryptography.*;
import static br.com.AllyWatch.server.Validator.PasswordValidator.validate;
import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeyService keyService;

    @Transactional
    public void add(UserRequest request) {

        validate(request.getPassword());
        verifyUnique(request);

        User newUser = User.builder().active(true).icon(NEUTRAL).build();

        KeyCrypt key = keyService.findKey();
        newUser.setCpf(
                encrypt(request.getCpf(), key.getPublicKey())
        );
        newUser.setFullname(
                encrypt(request.getFullname(), key.getPublicKey())
        );
        newUser.setEmail(
                encrypt(request.getEmail(), key.getPublicKey())
        );
        newUser.setKeys(key);

        userRepository.save(newUser);

        try {
            KeycloakUserManagement.createUser(
                    newUser.getId(), request.getFullname(), request.getEmail(), request.getPassword()
            );
        } catch (Exception e) {

            userRepository.delete(newUser);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "An error has occured.");
        }
    }

    public User getAuthenticatedUser(String authorization) {
        String email = getEmailFromJwtToken(authorization.substring(7));

        return userRepository.findAll().stream()
                .filter(u -> Objects.equals(decrypt(u.getEmail(), u.getKeys().getPrivateKey()), email))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found."));
    }

    public void editIcon(String authorization, IconRequest request) {
        User user = getAuthenticatedUser(authorization);

        if (request.getIcon() != MALE &&
                request.getIcon() != FEMALE &&
                request.getIcon() != NEUTRAL) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid icon type.");
        }

        user.setIcon(request.getIcon());

        userRepository.save(user);
    }

    public UserResponse getUserInfo(String authorization) {
        User user = getAuthenticatedUser(authorization);

        return UserResponse.builder()
                .name(
                        decrypt(user.getFullname(), user.getKeys().getPrivateKey())
                )
                .email(
                        decrypt(user.getEmail(), user.getKeys().getPrivateKey())
                )
                .icon(user.getIcon())
                .build();
    }

    public void delete(String authorization) {
        User user = getAuthenticatedUser(authorization);

        try {
            KeycloakUserManagement.deleteUser(
                    decrypt(user.getEmail(), user.getKeys().getPrivateKey())
            );
            userRepository.delete(user);
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "An error has occured.");
        }
    }

    public void verifyUnique(UserRequest request){
        List<User> users = userRepository.findAll();

        boolean hasUserWithSameEmail = users.stream().anyMatch(
                user -> Objects.equals(
                        decrypt(user.getEmail(), user.getKeys().getPrivateKey()),
                        request.getEmail()
                )
        );

        if (hasUserWithSameEmail){
            throw new ResponseStatusException(BAD_REQUEST, "Email already in use.");
        }

        boolean hasUserWithSameCPF = users.stream().anyMatch(
                user -> Objects.equals(
                        decrypt(user.getCpf(), user.getKeys().getPrivateKey()),
                        request.getCpf()
                )
        );

        if (hasUserWithSameCPF){
            System.out.println("cpf repetido");
            throw  new ResponseStatusException(BAD_REQUEST, "CPF already in use.");
        }
    }
}