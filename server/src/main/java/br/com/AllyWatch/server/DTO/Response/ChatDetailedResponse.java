package br.com.AllyWatch.server.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
public class ChatDetailedResponse {
    private long id;

    private boolean open;

    private String ally;

    private List<MessageResponse> messages;
}
