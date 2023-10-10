package br.com.AllyWatch.server.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.security.PrivateKey;
import java.security.PublicKey;

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
}
