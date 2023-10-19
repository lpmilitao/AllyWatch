package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.State;
import br.com.AllyWatch.server.Domain.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Lawyer {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String fullname;

    @Enumerated(STRING)
    private State seccional;

    @Column(unique = true, columnDefinition = "VARCHAR(2048)", nullable = false)
    private String oabRegisterNumber;

    @Column(columnDefinition = "VARCHAR(2048)")
    private String email;

    @Column(columnDefinition = "VARCHAR(2048)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(2048)")
    private String city;

    @Enumerated(STRING)
    private Status status;

    @ManyToOne @JoinColumn(name = "key_id")
    private KeyCrypt keys;
}
