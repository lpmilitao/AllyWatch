package br.com.AllyWatch.server.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class KeyCrypt {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(unique = true, columnDefinition = "VARBINARY(2048)", nullable = false)
    private PublicKey publicKey;

    @Column(unique = true, columnDefinition = "VARBINARY(2048)", nullable = false)
    private PrivateKey privateKey;

    @OneToMany(mappedBy = "keys")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "keys")
    private List<Lawyer> lawyers = new ArrayList<>();

    @OneToMany(mappedBy = "keys")
    private List<Psychologist> psychologists = new ArrayList<>();
}
