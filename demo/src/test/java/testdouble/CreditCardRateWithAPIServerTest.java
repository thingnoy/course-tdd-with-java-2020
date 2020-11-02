package testdouble;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

class CreditCardRateWithAPIServerTest {

    WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        setupStub();
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(get(urlEqualTo("/users/1"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/hello.json")));
    }

    @Test
    public void working_with_wiremock_200() {
        given().
                when().
                get("http://localhost:8090/users/1").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void working_with_wiremock_404() {
        given().
                when().
                get("http://localhost:8090/users/2").
                then().
                assertThat().statusCode(404);
    }
}