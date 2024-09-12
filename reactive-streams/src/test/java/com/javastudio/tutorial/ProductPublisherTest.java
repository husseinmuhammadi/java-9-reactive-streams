package com.javastudio.tutorial;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductPublisherTest {

    private final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());

    @BeforeEach
    void setUp() {
        wireMockServer.start();

        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Hello World\"}")));
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void test() throws IOException, InterruptedException {
        System.out.println(wireMockServer.baseUrl());
        System.out.println(wireMockServer.port());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(wireMockServer.baseUrl()))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
