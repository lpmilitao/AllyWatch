package br.com.AllyWatch.server.Validator;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PasswordValidator {

    private static final String REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static void validate(String password){
        boolean isValid = password.matches(REGEX);

        if(!isValid){
            throw new ResponseStatusException(BAD_REQUEST, "Invalid password");
        }
    }
}
