package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;

@Getter @Setter
public class AnonymousPostResponse extends PostResponse {

    private String authorName;

    private Icon icon;

    public AnonymousPostResponse(long id, String title, String body, String publicatedAt,
                                 List<CommentResponse> comments, int numberOfLikes) {
        super(id, title, body, publicatedAt, comments, numberOfLikes);
        this.authorName = "Ally";
        this.icon = NEUTRAL;
    }
}
