package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String fullname;

    private String cpf;

    private String email;

    private String password;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> commentsMade = new ArrayList<>();

    @ManyToMany(mappedBy = "likes")
    private List<Post> postsLiked = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message>  messagesSent = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Chat> chats = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Report>  reportsMade = new ArrayList<>();

}
