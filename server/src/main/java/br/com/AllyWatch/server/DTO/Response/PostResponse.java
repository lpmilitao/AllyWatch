package br.com.AllyWatch.server.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostResponse {

    private long id;

    private String title;

    private String body;

    private String publicatedAt;

    private List<CommentResponse> comments;

    private int numberOfLikes;

    public PostResponse(long id, String title, String body, String publicatedAt,
                        List<CommentResponse> comments, int numberOfLikes) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publicatedAt = publicatedAt;
        this.comments = comments;
        this.numberOfLikes = numberOfLikes;
    }
}
