package br.com.AllyWatch.server.Security;

import br.com.AllyWatch.server.Domain.User;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class KeycloakUserCreation {

    public static void main(User newUser) {
        // Configurações
        String keycloakBaseUrl = "http://localhost:8081/";
        String realm = "AllyWatch";
        String clientId = "allywatch-server";
        String clientSecret = "n2WKzhj91y5uLlvGT075tIGvupzHYKI7";

        // Construindo a URL para criar um usuário
        String createUserUrl = keycloakBaseUrl + "admin/realms/" + realm + "/users";

        // Montando o payload da requisição POST
        String requestBody = "{\"firstName\":" + newUser.getFullname() +
                ", \"email\":" + newUser.getEmail() +
                ", \"enabled\":true, \"username\":" + newUser.getEmail() + "}";

        // Configurando a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(createUserUrl))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken(keycloakBaseUrl, realm, clientId, clientSecret))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Enviando a requisição
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode == 201) {
                System.out.println("Usuário criado com sucesso!");
            } else {
                System.out.println("Erro ao criar o usuário. Código de status: " + statusCode);
                System.out.println(response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obter o token de acesso usando as credenciais da aplicação
    private static String getAccessToken(String keycloakBaseUrl, String realm, String clientId, String clientSecret) {
        // Construindo a URL para obter o token
        String tokenUrl = keycloakBaseUrl + "realms/" + realm + "/protocol/openid-connect/token";

        // Codificando as credenciais do cliente para o formato Base64
        String clientCredentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(clientCredentials.getBytes());

        // Montando o corpo da requisição para obter o token
        String requestBody = "grant_type=password&username=militao.luiza1505@gmail.com&password=Biel@t34m0";
        // Substitua "seu-username" e "sua-senha" pelos dados do usuário que tem permissões de administrador no seu realm.

        // Configurando a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Enviando a requisição
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode == 200) {
                String responseBody = response.body();
                // Extraindo o token de acesso do corpo da resposta
                return responseBody.split("\"access_token\":\"")[1].split("\"")[0];
            } else {
                System.out.println("Erro ao obter o token. Código de status: " + statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}