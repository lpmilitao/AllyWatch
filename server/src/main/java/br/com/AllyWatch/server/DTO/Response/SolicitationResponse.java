package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import br.com.AllyWatch.server.Domain.Enum.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class SolicitationResponse {

    private long id;

    private Status status;

    private String requestedUser;

    private String requestingUser;

    private Icon requestingUserIcon;
}
