package br.com.AllyWatch.server.Validator;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class RegisterNumberValidator {

    private static final String REGEX = "^\\d{2}/\\d{6}-IS$";

    public static void psychologistRegisterNumberValidator(String registerNumber){

        boolean isValid = registerNumber.matches(REGEX);

        if(!isValid){
            throw new ResponseStatusException(BAD_REQUEST, "Invalid register number.");
        }
    }
}
