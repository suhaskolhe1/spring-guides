package com.example.gsrestservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest
@AutoConfigureRestTestClient
public class GreetingControllerTest {
    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
        this.restTestClient.get().uri("/greeting")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.content").isEqualTo("Hello, World!");
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
        this.restTestClient.get()
                .uri(uri ->
                        uri.path("/greeting").queryParam("name", "Suhas").build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.content").isEqualTo("Hello, Suhas!");
    }

}
