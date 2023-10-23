package br.com.AllyWatch.server.Domain;

import br.com.AllyWatch.server.Domain.Enum.Type;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Psychologist extends Specialist{

    @Enumerated(STRING)
    private Type type;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String cpfOrCpnj;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String registerNumber;

    @ManyToOne @JoinColumn(name = "key_id")
    private KeyCrypt keys;
}
