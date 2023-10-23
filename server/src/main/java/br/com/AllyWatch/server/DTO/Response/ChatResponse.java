package br.com.AllyWatch.server.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ChatResponse {

    private long id;

    private boolean open;

    //Ally seria o usuário que está conversando com o usuário logado.
    private String ally;
}
