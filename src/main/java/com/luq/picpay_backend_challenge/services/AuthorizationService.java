package com.luq.picpay_backend_challenge.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luq.picpay_backend_challenge.dto.response.AuthorizationResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;

@Service
public class AuthorizationService {
    public AuthorizationResponse authorize() {
        String url = "https://util.devi.tools/api/v2/authorize";
        HttpClient client = newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .version(HttpClient.Version.HTTP_2)
            .GET()
            .build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), AuthorizationResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
