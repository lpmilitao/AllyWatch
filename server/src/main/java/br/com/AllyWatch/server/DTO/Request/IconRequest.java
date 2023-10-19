package br.com.AllyWatch.server.DTO.Request;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IconRequest {

    @NotBlank
    private Icon icon;
}
