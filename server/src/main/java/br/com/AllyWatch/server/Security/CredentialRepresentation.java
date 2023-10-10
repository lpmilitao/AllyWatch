package br.com.AllyWatch.server.Security;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CredentialRepresentation {

    private String type;

    private String value;

    private boolean temporary;

    public CredentialRepresentation(String value){
        this.type = "password";
        this.value = value;
        this.temporary = false;
    }

    @Override
    public String toString() {
        return "{ " +
                "\"type\": \"" + type + "\"," +
                " \"value\": \"" + value + "\"" +
                ", \"temporary: \"" + temporary + "\" }";
    }
}
