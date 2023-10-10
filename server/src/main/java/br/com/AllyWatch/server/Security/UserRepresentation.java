package br.com.AllyWatch.server.Security;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserRepresentation {

    private long id;

    private String email;

    private String username;

    private String firstName;

    private List<CredentialRepresentation> credentials = new ArrayList<>();

//    private List<String> clientRoles = new ArrayList<>();

    private boolean enabled;

    @Override
    public String toString() {
        return "{" +
                " \"id\": \"" + id +
                "\", \"email\": \"" + email +
                "\", \"username\": \"" + username +
                "\", \"firstName\": \"" + firstName +
                "\", \"credentials\": " + credentials.toString() +
//                ", \"clientRoles\": \"" + clientRoles +
                "\", \"enabled\": \"" + enabled +
                "\" }";
    }
}