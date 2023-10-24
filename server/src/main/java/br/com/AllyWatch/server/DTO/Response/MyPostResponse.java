package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MyPostResponse extends PublicPostResponse {
    private List<String> likedBy;

    public MyPostResponse(long id, String title, String body, String publicatedAt,
                          List<CommentResponse> comments, int numberOfLikes, String author, Icon icon,
                          List<String> likedBy) {
        super(id, title, body, publicatedAt, comments, numberOfLikes, author, icon);
        this.likedBy = likedBy;
    }
}
