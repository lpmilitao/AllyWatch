package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostResponse {

    private long id;

    private String body;

    private String publicatedAt;

    private int numberOfLikes;

    private boolean mine;

    private String author;
    private Icon icon;

    private boolean anonymous;

    private boolean likedByMe;

    public PostResponse(long id, String body, String publicatedAt, int numberOfLikes, boolean mine,
                        String author, Icon icon, boolean anonymous, boolean likedByMe) {
        this.id = id;
        this.body = body;
        this.publicatedAt = publicatedAt;
        this.numberOfLikes = numberOfLikes;
        this.mine = mine;
        this.author = author;
        this.icon = icon;
        this.anonymous = anonymous;
        this.likedByMe = likedByMe;
    }
}
