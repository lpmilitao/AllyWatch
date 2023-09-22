package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Report {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "text")
    private String body;

    private boolean checked;

    private LocalDateTime reportTime;

    @ManyToOne @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne @JoinColumn(name = "author_id")
    private User author;
}
