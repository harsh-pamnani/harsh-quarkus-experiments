package main.externalhttpcall;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@QuarkusTest
class JsonPlaceholderServiceTest {
    @Inject
    JsonPlaceholderService jsonPlaceholderService;

    static WireMockServer wireMockServer;

    @BeforeAll
    static void setupClassBefore() {
        wireMockServer =
                new WireMockServer(); // If no arguments are supplied, port defaults to "8080", and host defaults to
        // "localhost".
        wireMockServer.start();
    }

    @AfterAll
    static void setupClassAfter() {
        wireMockServer.stop();
    }

    @Test
    void testMakeDummyJsonPlaceholderCall() throws IOException {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withBody("Welcome to Baeldung!")));
        jsonPlaceholderService.makeDummyJsonPlaceholderCall();
        verify(getRequestedFor(urlEqualTo("/")));
    }
}
