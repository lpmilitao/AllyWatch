package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.ChatResponse;
import br.com.AllyWatch.server.Domain.Chat;
import br.com.AllyWatch.server.Domain.User;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class ChatMapper {

    public static ChatResponse toResponse(Chat entity, User user){
        return ChatResponse.builder()
                .id(entity.getId())
                .open(entity.isOpen())
                .ally(
                        decrypt(user.getFullname(), user.getKeys().getPrivateKey())
                )
                .build();
    }
}
