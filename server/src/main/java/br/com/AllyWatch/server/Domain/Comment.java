package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "text")
    private String comment;

    private LocalDateTime publicationTime;

    @ManyToOne @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne @JoinColumn(name = "author_id")
    private User author;
}
