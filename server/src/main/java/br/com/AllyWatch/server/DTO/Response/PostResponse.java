package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
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

    private boolean mine;

    private String author;

    private Icon icon;

    private boolean anonymous;

    public PostResponse(long id, String title, String body, String publicatedAt, List<CommentResponse> comments,
                        int numberOfLikes, boolean mine, String author, Icon icon, boolean anonymous) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publicatedAt = publicatedAt;
        this.comments = comments;
        this.numberOfLikes = numberOfLikes;
        this.mine = mine;
        this.author = author;
        this.icon = icon;
        this.anonymous = anonymous;
    }
}
