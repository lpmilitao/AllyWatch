package br.com.AllyWatch.server.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class PostResponse {

    private long id;

    private String title;

    private String body;

    private LocalDateTime publicationTime;

    private List<CommentResponse> comments;

    private int numberOfLikes;

    public PostResponse(long id, String title, String body, LocalDateTime publicationTime,
                        List<CommentResponse> comments, int numberOfLikes) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publicationTime = publicationTime;
        this.comments = comments;
        this.numberOfLikes = numberOfLikes;
    }
}
