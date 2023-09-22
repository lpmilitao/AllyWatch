package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;

    private String body;

    private String aggressor;

    private LocalDateTime publicationTime;

    private boolean anonymous;

    @ManyToOne @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany @JoinTable(name = "user_likes_post",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<User> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Report> reports = new ArrayList<>();
}
