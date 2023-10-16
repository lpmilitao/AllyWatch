package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.CommentResponse;
import br.com.AllyWatch.server.Domain.Comment;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class CommentMapper {

    public static CommentResponse toResponse(Comment entity){
        return CommentResponse.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .publicationTime(entity.getPublicationTime())
                .author(
                        decrypt(entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey())
                )
                .build();
    }
}
