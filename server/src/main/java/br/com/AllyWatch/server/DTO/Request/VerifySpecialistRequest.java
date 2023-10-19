package br.com.AllyWatch.server.DTO.Request;

import br.com.AllyWatch.server.Domain.Enum.SpecialistType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerifySpecialistRequest {

    @NotNull
    private boolean accepted;

    @NotNull
    private SpecialistType type;
}
