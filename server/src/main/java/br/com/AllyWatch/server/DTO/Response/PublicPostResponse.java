package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class PublicPostResponse extends PostResponse{

    private String author;

    private Icon icon;

    public PublicPostResponse(long id, String title, String body, LocalDateTime publicationTime,
                              List<CommentResponse> comments, int numberOfLikes, String author, Icon icon) {
        super(id, title, body, publicationTime, comments, numberOfLikes);
        this.author = author;
        this.icon = icon;
    }
}
