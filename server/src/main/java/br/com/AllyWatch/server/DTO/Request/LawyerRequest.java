package br.com.AllyWatch.server.DTO.Request;

import br.com.AllyWatch.server.Domain.Enum.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LawyerRequest {

    @NotBlank
    private String fullname;

    @NotNull
    private State seccional;

    @NotBlank
    private String oabRegisterNumber;

    @NotBlank
    private String email;

    private String phone;

    @NotBlank
    private String city;
}
