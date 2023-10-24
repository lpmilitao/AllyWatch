package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Request.MessageRequest;
import br.com.AllyWatch.server.DTO.Response.MessageResponse;
import br.com.AllyWatch.server.Domain.Message;

import java.time.LocalDateTime;

public class MessageMapper {
    public static Message toEntity(MessageRequest request) {
        return Message.builder()
                .message(request.getMessage())
                .sentTime(LocalDateTime.now())
                .build();
    }

    public static MessageResponse toResponse(Message entity, long userId){
        return MessageResponse.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .sentAt(entity.getSentTime())
                .sentByMe(
                        entity.getSender().getId() == userId
                )
                .build();
    }
}
