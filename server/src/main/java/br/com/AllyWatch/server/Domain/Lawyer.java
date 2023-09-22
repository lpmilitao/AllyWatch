package br.com.AllyWatch.server.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Lawyer {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String fullname;

    @Enumerated(STRING)
    private State seccional;

    private String oabRegisterNumber;

    private String email;

    private String phone;

    private String city;

    @Enumerated(STRING)
    private Status status;
}
