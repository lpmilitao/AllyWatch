package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Request.PostRequest;
import br.com.AllyWatch.server.DTO.Response.MyPostResponse;
import br.com.AllyWatch.server.DTO.Response.PostResponse;
import br.com.AllyWatch.server.Domain.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;
import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class PostMapper {

    public static Post toEntity(PostRequest request) {
        return Post.builder()
                .body(request.getBody())
                .anonymous(request.isAnonymous())
                .publicationTime(LocalDateTime.now())
                .build();
    }

    public static MyPostResponse toMyResponse(Post entity, boolean liked) {
        return new MyPostResponse(
                entity.getId(),
                entity.getBody(),
                entity.getPublicationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                entity.getComments().stream().map(CommentMapper::toResponse).toList(),
                entity.getLikes().size(),
                true,
                decrypt(entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey()),
                entity.getAuthor().getIcon(),
                entity.isAnonymous(),
                liked,
                entity.getLikes().stream().map(user ->
                        decrypt(user.getFullname(), user.getKeys().getPrivateKey())
                ).toList()
        );
    }

    public static PostResponse toAnonymousResponse(Post entity, boolean liked) {
        return new PostResponse(
                entity.getId(),
                entity.getBody(),
                entity.getPublicationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                entity.getComments().stream().map(CommentMapper::toResponse).toList(),
                entity.getLikes().size(),
                false,
                "Ally",
                NEUTRAL,
                true,
                liked
        );
    }

    public static PostResponse toPublicResponse(Post entity, boolean liked) {
        return new PostResponse(
                entity.getId(),
                entity.getBody(),
                entity.getPublicationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                entity.getComments().stream().map(CommentMapper::toResponse).toList(),
                entity.getLikes().size(),
                false,
                decrypt(
                        entity.getAuthor().getFullname(), entity.getAuthor().getKeys().getPrivateKey()
                ),
                entity.getAuthor().getIcon(),
                false,
                liked
        );
    }
}
