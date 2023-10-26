package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.LoginRequest;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Security.KeycloakUserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public String login(LoginRequest request) {
        try {
            return KeycloakUserManagement.login(request);
        } catch (Exception e) {
            throw new ResponseStatusException(UNAUTHORIZED,
                    "Something went wrong on the authorization proccess.");
        }
    }

    public void logout(String authorization){
        User user = userService.getAuthenticatedUser(authorization);

        try {
            KeycloakUserManagement.logout(
                    decrypt(user.getEmail(), user.getKeys().getPrivateKey())
            );
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "An error has occured.");
        }
    }
}
