package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Request.CreatePostRequest;
import br.com.AllyWatch.server.Domain.Post;

import java.time.LocalDateTime;

public class PostMapper {

    public static Post toEntity(CreatePostRequest request){
        return Post.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .anonymous(request.isAnonymous())
                .publicationTime(LocalDateTime.now())
                .build();
    }

}
