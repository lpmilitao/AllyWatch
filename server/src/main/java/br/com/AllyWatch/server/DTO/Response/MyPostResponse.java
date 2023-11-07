package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MyPostResponse extends PostResponse {
    private List<String> likedBy;

    public MyPostResponse(long id, String title, String body, String publicatedAt, List<CommentResponse> comments,
                          int numberOfLikes, boolean mine, String author, Icon icon, boolean anonymous, List<String> likedBy) {
        super(id, title, body, publicatedAt, comments, numberOfLikes, mine, author, icon, anonymous);
        this.likedBy = likedBy;
    }
}
