package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.LoginRequest;
import br.com.AllyWatch.server.Security.KeycloakUserManagement;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class AuthService {

    public String login(LoginRequest request) {
        try {
            return KeycloakUserManagement.login(request);
        } catch (Exception e) {
            throw new ResponseStatusException(UNAUTHORIZED,
                    "Something went wrong on the authorization proccess.");
        }
    }
}
