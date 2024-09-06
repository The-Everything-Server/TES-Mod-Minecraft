package com.thesaltynewfie.tesmod.network;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.thesaltynewfie.tesmod.config.ConfigHelper;
import com.thesaltynewfie.tesmod.Types.Response;
import net.fabricmc.loader.api.FabricLoader;

public class Network {
    private static final HttpClient _client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static Response Get(String url) throws Exception {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "tesmod.ini");
        ConfigHelper conf = new ConfigHelper(configFile);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", conf.getValue("api" ,"token", "none"))
                .GET()
                .build();

        HttpResponse<String> response = _client.send(request, HttpResponse.BodyHandlers.ofString());

        Response resp = new Response();
        resp.setStatusCode(response.statusCode());
        resp.setBody(response.body());

        return resp;
    }

    public static Response Post(String url, String Data) throws Exception {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "tesmod.ini");
        ConfigHelper conf = new ConfigHelper(configFile);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", conf.getValue("api" ,"token", "none"))
                .POST(HttpRequest.BodyPublishers.ofString(Data))
                .build();

        HttpResponse<String> response = _client.send(request, HttpResponse.BodyHandlers.ofString());

        Response resp = new Response();
        resp.setStatusCode(response.statusCode());
        resp.setBody(response.body());

        return resp;
    }
}
