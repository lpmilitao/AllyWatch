package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.CommentResponse;
import br.com.AllyWatch.server.Domain.Comment;

import java.time.format.DateTimeFormatter;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class CommentMapper {

    public static CommentResponse toResponse(Comment entity, long userId) {
        return CommentResponse.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .publicationTime(
                        entity.getPublicationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
                )
                .author(
                        decrypt(entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey())
                )
                .mine(userId == entity.getAuthor().getId())
                .build();
    }
}
