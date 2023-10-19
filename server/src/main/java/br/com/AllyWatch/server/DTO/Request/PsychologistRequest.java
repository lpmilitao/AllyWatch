package br.com.AllyWatch.server.DTO.Request;

import br.com.AllyWatch.server.Domain.Enum.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PsychologistRequest extends SpecialistRequest {

    @NotNull
    private Type type;

    @NotBlank
    private String cpfOrCpnj;

    @NotBlank
    private String registerNumber;
}
