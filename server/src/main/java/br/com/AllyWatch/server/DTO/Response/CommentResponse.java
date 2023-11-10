package br.com.AllyWatch.server.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class CommentResponse {

    private long id;

    private String comment;

    private String publicationTime;

    private String author;

    private boolean mine;
}
