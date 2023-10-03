package br.com.AllyWatch.server.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CredentialRepresentation {

    private String type;
    private String value;

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
