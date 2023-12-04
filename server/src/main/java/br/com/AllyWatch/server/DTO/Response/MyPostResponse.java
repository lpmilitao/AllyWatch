package br.com.AllyWatch.server.DTO.Response;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MyPostResponse extends PostResponse {

    private List<String> likedBy;

    public MyPostResponse(long id, String body, String publicatedAt, int numberOfLikes, boolean mine, String author,
                          Icon icon, boolean anonymous, boolean likedByMe, List<String> likedBy) {
        super(id, body, publicatedAt, numberOfLikes, mine, author, icon, anonymous, likedByMe);
        this.likedBy = likedBy;
    }
}
