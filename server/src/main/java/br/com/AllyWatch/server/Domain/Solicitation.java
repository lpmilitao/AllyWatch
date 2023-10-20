package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Solicitation {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Enumerated(STRING)
    private Status status;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "chat_id")
    private Chat chat;
}
