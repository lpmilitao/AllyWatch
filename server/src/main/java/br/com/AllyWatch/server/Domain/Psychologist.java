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

    private String fullname;

    private String email;

    @Enumerated(STRING)
    private Type type;

    private String cpfOrCpnj;

    private String registerNumber;

    @Enumerated(STRING)
    private State state;

    private String city;

    @Enumerated(STRING)
    private Status status;
}
