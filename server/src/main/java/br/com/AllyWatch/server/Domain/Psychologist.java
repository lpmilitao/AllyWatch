package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.State;
import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.Enum.Type;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Psychologist {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String fullname;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String email;

    @Enumerated(STRING)
    private Type type;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String cpfOrCpnj;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String registerNumber;

    @Enumerated(STRING)
    private State state;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String city;

    @Enumerated(STRING)
    private Status status;
}
