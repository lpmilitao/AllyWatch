package br.com.AllyWatch.server.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class CommentResponse {

    private long id;

    private String comment;

    private LocalDateTime publicationTime;

    private String author;
}
