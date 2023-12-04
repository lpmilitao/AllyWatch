package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.CommentResponse;
import br.com.AllyWatch.server.Domain.Comment;

import java.time.format.DateTimeFormatter;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class CommentMapper {

    public static CommentResponse toResponse(Comment entity, long userId, long postAuthorId, boolean isPostAnonymous) {
        String authorName = decrypt(entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey());

        if(postAuthorId == entity.getAuthor().getId() && isPostAnonymous){
            authorName = "Ally";
        }

        return CommentResponse.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .publicationTime(
                        entity.getPublicationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
                )
                .author(authorName)
                .mine(userId == entity.getAuthor().getId())
                .build();
    }
}
