package com.thesaltynewfie.tesmod.network;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.thesaltynewfie.tesmod.global.global;

public class Network {
    private static final HttpClient _client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static String Get(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiVG9ieSIsImlhdCI6MTcyNDk1MDQxNX0.ibJWN1drtK7i6igj6cCPMWbH1eUzm56OcPqzerTIywA")
                .GET()
                .build();

        HttpResponse<String> response = _client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static String Post(String url, String Data) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiVG9ieSIsImlhdCI6MTcyNDk1MDQxNX0.ibJWN1drtK7i6igj6cCPMWbH1eUzm56OcPqzerTIywA")
                .POST(HttpRequest.BodyPublishers.ofString(Data))
                .build();

        HttpResponse<String> response = _client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
