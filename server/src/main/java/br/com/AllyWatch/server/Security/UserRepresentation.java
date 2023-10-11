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

    private boolean enabled;
}