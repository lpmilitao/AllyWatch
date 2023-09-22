package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Chat {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

    @ManyToMany @JoinTable(name = "user_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<User> users = new ArrayList<>();
}
