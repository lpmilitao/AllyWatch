package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Icon.NEUTRAL;

@Getter @Setter
public class AnonymousPostResponse extends PostResponse {

    private String authorName;

    private Icon icon;

    public AnonymousPostResponse(long id, String title, String body, LocalDateTime publicationTime,
                                 List<CommentResponse> comments, int numberOfLikes) {
        super(id, title, body, publicationTime, comments, numberOfLikes);
        this.authorName = "Ally";
        this.icon = NEUTRAL;
    }
}
