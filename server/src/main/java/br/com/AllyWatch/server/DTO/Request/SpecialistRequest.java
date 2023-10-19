package br.com.AllyWatch.server.DTO.Request;

import br.com.AllyWatch.server.Domain.Enum.State;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SpecialistRequest {

    @NotBlank
    private String fullname;

    @NotNull
    private State state;

    @NotBlank
    private String city;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String phone;
}
