package br.com.AllyWatch.server.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Lawyer extends Specialist{

    @Column(unique = true, columnDefinition = "VARCHAR(2048)", nullable = false)
    private String oabRegisterNumber;

    @ManyToOne @JoinColumn(name = "key_id")
    private KeyCrypt keys;
}
