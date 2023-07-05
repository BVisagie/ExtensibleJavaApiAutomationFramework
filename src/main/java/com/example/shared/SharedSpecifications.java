package com.example.shared;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import static com.example.restful_booker.shared.RestfulBookerApiConstants.AUTHORIZATION;

/**
 * Specification in REST Assured
 * You can use specification objects to avoid duplicate request parameters and responses
 * in case you are working on multiple tests. You can use the same object every time you make a different request.
 */
public class SharedSpecifications {
    private SharedSpecifications() {
    }

    public static RequestSpecification generalRequestSpecAuthNoBody(String authToken) {

        return new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader(AUTHORIZATION, authToken).
                build();
    }

    public static RequestSpecification generalRequestSpecNoHeaderNoBody() {

        return new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                build();
    }

    public static ResponseSpecification generalStatusOkResponseSpec(long expectedResponseTimeMs) {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                expectResponseTime(Matchers.lessThan(expectedResponseTimeMs)).
                build();
    }

    public static ResponseSpecification generalStatusCreatedResponseSpec(long expectedResponseTimeMs) {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_CREATED).
                expectResponseTime(Matchers.lessThan(expectedResponseTimeMs)).
                build();
    }

    public static ResponseSpecification generalStatusUnauthorizedResponseSpec(long expectedResponseTimeMs) {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_FORBIDDEN).
                expectResponseTime(Matchers.lessThan(expectedResponseTimeMs)).
                build();
    }

    public static ResponseSpecification generalResponseSpecNoStatusEnforced(long expectedResponseTimeMs) {
        return new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectResponseTime(Matchers.lessThan(expectedResponseTimeMs)).
                build();
    }

    public static ResponseSpecification generalStatusOkOnlyResponseSpec(long expectedResponseTimeMs) {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                expectResponseTime(Matchers.lessThan(expectedResponseTimeMs)).
                build();
    }
}
