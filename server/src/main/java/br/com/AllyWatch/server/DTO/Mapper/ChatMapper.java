package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.ChatDetailedResponse;
import br.com.AllyWatch.server.DTO.Response.ChatResponse;
import br.com.AllyWatch.server.DTO.Response.MessageResponse;
import br.com.AllyWatch.server.Domain.Chat;
import br.com.AllyWatch.server.Domain.User;

import java.util.Comparator;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class ChatMapper {

    public static ChatResponse toResponse(Chat entity, User user) {
        return ChatResponse.builder()
                .id(entity.getId())
                .open(entity.isOpen())
                .ally(
                        decrypt(user.getFullname(), user.getKeys().getPrivateKey())
                )
                .allyIcon(user.getIcon())
                .build();
    }

    public static ChatDetailedResponse toDetailedResponse(Chat entity, User user, User ally) {
        return ChatDetailedResponse.builder()
                .id(entity.getId())
                .open(entity.isOpen())
                .ally(
                        decrypt(ally.getFullname(), ally.getKeys().getPrivateKey())
                )
                .messages(
                        entity.getMessages().stream().map(
                                        message -> MessageMapper.toResponse(message, user.getId()))
                                .sorted(Comparator.comparing(MessageResponse::getSentAt))
                                .toList()
                )
                .build();
    }
}
