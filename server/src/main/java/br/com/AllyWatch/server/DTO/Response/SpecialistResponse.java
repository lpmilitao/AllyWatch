package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.State;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter @Setter
public class SpecialistResponse {

    private long id;

    private String fullname;

    private State state;

    private String email;

    private String phone;

    private String city;
}
