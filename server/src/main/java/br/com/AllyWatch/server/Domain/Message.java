package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Message {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "text")
    private String message;

    private LocalDateTime sentTime;

    @ManyToOne @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne @JoinColumn(name = "chat_id")
    private Chat chat;
}
