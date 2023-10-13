package br.com.AllyWatch.server.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter
public class CreateUserRequest {

    @NotBlank
    private String fullname;

    @NotBlank @CPF
    private String cpf;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;
}