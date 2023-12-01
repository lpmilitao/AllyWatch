package br.com.AllyWatch.server.Security;

import br.com.AllyWatch.server.DTO.Request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class KeycloakUserManagement {

    private static final String BASE_URL = "http://localhost:8081/";
    private static final String REALM = "AllyWatch";
    private static final String CLIENT_ID = "admin-cli";
    private static final String CLIENT_SECRET = System.getenv("keycloak_client_secret");;

    public static void createUser(long id, String fullname, String email, String password)
            throws Exception {
        String createUserUrl = BASE_URL + "admin/realms/" + REALM + "/users";

        List<CredentialRepresentation> credentials = new ArrayList<>();
        credentials.add(new CredentialRepresentation(password));

        UserRepresentation requestBody = UserRepresentation.builder()
                .id(id)
                .firstName(fullname)
                .email(email)
                .username(email)
                .credentials(credentials)
                .enabled(true)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJSON = objectMapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUserUrl))
                .header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, "Bearer " + getAccessToken())
                .POST(HttpRequest.BodyPublishers.ofString(requestJSON))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void deleteUser(String username) throws Exception {
        String createUserUrl = BASE_URL + "admin/realms/" + REALM + "/users/"
                + getUserIdByUsername(username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUserUrl))
                .header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, "Bearer " + getAccessToken())
                .DELETE()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void logout(String username) throws Exception {
        String createUserUrl = BASE_URL + "admin/realms/" + REALM + "/users/"
                + getUserIdByUsername(username) + "/logout";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUserUrl))
                .header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, "Bearer " + getAccessToken())
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static String login(LoginRequest login) throws Exception {
        String tokenUrl = BASE_URL + "realms/"+ REALM +"/protocol/openid-connect/token";

        String requestBody = "grant_type=password&" +
                "username="+ login.getEmail()+"&" +
                "password="+ login.getPassword()+"&" +
                "client_id="+ CLIENT_ID +"&" +
                "client_secret="+ CLIENT_SECRET;

        System.out.println(CLIENT_SECRET);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header(CONTENT_TYPE, "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().split("\"access_token\":\"")[1].split("\"")[0];
    }

    private static String getUserIdByUsername(String username) throws Exception {
        String createUserUrl = BASE_URL + "admin/realms/" + REALM + "/users?username=" + username;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUserUrl))
                .header(CONTENT_TYPE, "application/json")
                .header(AUTHORIZATION, "Bearer " + getAccessToken())
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().substring(8, 44);
    }

    private static String getAccessToken() {
        String tokenUrl = BASE_URL + "realms/master/protocol/openid-connect/token";

        String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedCredentials = Base64.getEncoder().encodeToString(clientCredentials.getBytes());

        String requestBody = "grant_type=client_credentials";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header(AUTHORIZATION, "Basic " + encodedCredentials)
                .header(CONTENT_TYPE, "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode == 200) {
                String responseBody = response.body();
                return responseBody.split("\"access_token\":\"")[1].split("\"")[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}