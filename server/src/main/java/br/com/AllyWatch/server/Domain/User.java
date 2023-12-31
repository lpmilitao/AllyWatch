package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.Icon;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity @EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String fullname;

    @Column(unique = true, columnDefinition = "VARCHAR(2048)", nullable = false)
    private String cpf;

    @Column(unique = true, columnDefinition = "VARCHAR(2048)", nullable = false)
    private String email;

    @Enumerated(STRING)
    private Icon icon;

    private boolean active;

    @ManyToOne @JoinColumn(name = "key_id")
    private KeyCrypt keys;

    @OneToMany(mappedBy = "author", cascade = REMOVE)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = REMOVE)
    private List<Comment> commentsMade = new ArrayList<>();

    @ManyToMany(mappedBy = "likes", cascade = REMOVE)
    private List<Post> postsLiked = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = REMOVE)
    private List<Message>  messagesSent = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = REMOVE)
    private List<Chat> chats = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = REMOVE)
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

    public void addChat(Chat chat){
        this.chats.add(chat);
    }

    public void addPostsLiked(Post post){
        this.postsLiked.add(post);
    }

    public void removePostsLiked(Post post){
        this.postsLiked.remove(post);
    }
}