package br.com.AllyWatch.server.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class MessageResponse {
    private long id;

    private String message;

    private LocalDateTime sentAt;

    private boolean sentByMe;
}
