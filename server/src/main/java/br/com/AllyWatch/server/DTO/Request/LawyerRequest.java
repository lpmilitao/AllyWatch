package br.com.AllyWatch.server.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LawyerRequest extends SpecialistRequest{

    @NotBlank
    private String oabRegisterNumber;
}
