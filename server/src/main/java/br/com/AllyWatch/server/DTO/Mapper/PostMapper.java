package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Request.PostRequest;
import br.com.AllyWatch.server.DTO.Response.AnonymousPostResponse;
import br.com.AllyWatch.server.DTO.Response.MyPostResponse;
import br.com.AllyWatch.server.DTO.Response.PublicPostResponse;
import br.com.AllyWatch.server.Domain.Post;

import java.time.LocalDateTime;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class PostMapper {

    public static Post toEntity(PostRequest request) {
        return Post.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .anonymous(request.isAnonymous())
                .publicationTime(LocalDateTime.now())
                .build();
    }

    public static MyPostResponse toMyResponse(Post entity) {
        return new MyPostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                entity.getPublicationTime(),
                entity.getComments().stream().map(CommentMapper::toResponse).toList(),
                entity.getLikes().size(),
                decrypt(entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey()),
                entity.getAuthor().getIcon(),
                entity.getLikes().stream().map(user ->
                        decrypt(user.getFullname(), user.getKeys().getPrivateKey())
                ).toList()
        );
    }

    public static AnonymousPostResponse toAnonymousResponse(Post entity) {
        return new AnonymousPostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                entity.getPublicationTime(),
                entity.getComments().stream().map(CommentMapper::toResponse).toList(),
                entity.getLikes().size()
        );
    }

    public static PublicPostResponse toPublicResponse(Post entity) {
        return new PublicPostResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                entity.getPublicationTime(),
                entity.getComments().stream().map(CommentMapper::toResponse).toList(),
                entity.getLikes().size(),
                decrypt(entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey()),
                entity.getAuthor().getIcon()
        );
    }
}
