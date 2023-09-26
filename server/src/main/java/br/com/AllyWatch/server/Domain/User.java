package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String fullname;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(STRING)
    private Icon icon;

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


    public void addPost(Post post){
        this.posts.add(post);
        post.setAuthor(this);
    }

    public void addCommentMade(Comment commentMade){
        this.commentsMade.add(commentMade);
        commentMade.setAuthor(this);
    }

    public void addMessage(Message message){
        this.messagesSent.add(message);
        message.setSender(this);
    }

    public void addReport(Report report){
        this.reportsMade.add(report);
        report.setAuthor(this);
    }
}