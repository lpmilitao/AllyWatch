package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.State;
import br.com.AllyWatch.server.Domain.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@Getter @Setter
public class Specialist {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String fullname;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String email;

    @Column(columnDefinition = "VARCHAR(2048)")
    private String phone;

    @Enumerated(STRING)
    private State state;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String city;

    @Enumerated(STRING)
    private Status status;
}
