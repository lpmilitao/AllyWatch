package br.com.AllyWatch.server.Security;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserRepresentation {

    private long id;

    private String email;

    private String username;

    private String firstName;

    private List<CredentialRepresentation> credentials = new ArrayList<>();

    private Map<String, String> clientRoles = new HashMap<>();

    private boolean enabled = true;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", credentials= " + credentials +
                ", clientRoles=" + clientRoles +
                ", enabled=" + enabled +
                "}";
    }
}