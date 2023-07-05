package nl.kza.tests.restful_booker;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import nl.kza.restful_booker.config.RestfulBookerApiConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class RestfulBookerBaseApi {
    protected static RestfulBookerApiConfig restfulBookerApiConfig;
    protected static Random randomSeed = new Random();

    // Keep note: @BeforeAll runs once before every test class is executed
    @BeforeAll
    public static void beforeAllTestsPerClass() {

        // This enables debug logging to console of all API activity requests etc.
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        restfulBookerApiConfig = ConfigFactory.create(RestfulBookerApiConfig.class);

        baseURI = restfulBookerApiConfig.baseUri();
        port = restfulBookerApiConfig.apiPort();
    }

    @AfterAll
    public static void cleanupAfterAllTestsInClass() {
        // Nothing to do at this time
    }
}
