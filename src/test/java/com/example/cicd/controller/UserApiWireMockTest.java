package com.example.cicd.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class UserApiWireMockTest {

    static WireMockServer wm;

    @BeforeAll 
    static void setup() {
        wm = new WireMockServer(options().dynamicPort());
        wm.start();
    }

    @BeforeAll 
    static void teardown() {
        if (wm != null) wm.stop();
    }

    @AfterEach 
    void reset() {
        if (wm != null) wm.resetAll();
    }

    @Test
    void getUser_success_return200_andUsernameIsRikiboatz() throws Exception {
        wm.stubFor(get(urlPathEqualTo("/api/users/getusername/1"))
            .willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type","application/json")
                .withBody("{\"username\":\"Rikiboatz\"}")));

        var client = java.net.http.HttpClient.newHttpClient();
        var request = java.net.http.HttpRequest.newBuilder()
            .uri(java.net.URI.create("http://localhost:" + wm.port() + "/api/users/getusername/1"))
            .GET()
            .build();

        var response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertEquals("{\"username\":\"Rikiboatz\"}", response.body());

        // (ทางเลือก) ตรวจว่า endpoint ถูกเรียกจริง 1 ครั้ง
        wm.verify(getRequestedFor(urlPathEqualTo("/api/users/getusername/1")));
    }

    @Test
    void getUser_failed_return404_notfound() throws Exception {
        wm.stubFor(get(urlPathEqualTo("/api/users/getusername/99"))
            .willReturn(aResponse().withStatus(404)
                .withHeader("Content-Type","application/json")
                .withBody("{\"code\":\"NOT_FOUND_ERROR\", \"message\":\"User not found\"}")));

        var client = java.net.http.HttpClient.newHttpClient();
        var request = java.net.http.HttpRequest.newBuilder()
            .uri(java.net.URI.create("http://localhost:" + wm.port() + "/api/users/getusername/1"))
            .GET()
            .build();

        var response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());

        // (ทางเลือก) ตรวจว่า endpoint ถูกเรียกจริง 1 ครั้ง
        wm.verify(getRequestedFor(urlPathEqualTo("/api/users/getusername/1")));
    }
}
