package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PublicPostResponse extends PostResponse{

    private String author;

    private Icon icon;

    public PublicPostResponse(long id, String title, String body, String publicatedAt,
                              List<CommentResponse> comments, int numberOfLikes, String author, Icon icon) {
        super(id, title, body, publicatedAt, comments, numberOfLikes);
        this.author = author;
        this.icon = icon;
    }
}
