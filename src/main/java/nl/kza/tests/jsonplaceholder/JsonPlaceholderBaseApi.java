package nl.kza.tests.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import nl.kza.jsonplaceholder.config.JsonPlaceholderApiConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class JsonPlaceholderBaseApi {
    protected static JsonPlaceholderApiConfig jsonPlaceholderApiConfig;
    protected static Random randomSeed = new Random();

    // Keep note: @BeforeAll runs once before every test class is executed
    @BeforeAll
    public static void beforeAllTestsPerClass() {

        // This enables debug logging to console of all API activity requests etc.
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        jsonPlaceholderApiConfig = ConfigFactory.create(JsonPlaceholderApiConfig.class);

        baseURI = jsonPlaceholderApiConfig.baseUri();
        port = jsonPlaceholderApiConfig.apiPort();
    }

    @AfterAll
    public static void cleanupAfterAllTestsInClass() {
        // Nothing to do at this time
    }
}
