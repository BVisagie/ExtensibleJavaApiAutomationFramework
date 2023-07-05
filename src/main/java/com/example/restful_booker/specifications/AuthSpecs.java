package com.example.restful_booker.specifications;

import com.example.restful_booker.model.AuthUser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AuthSpecs {
    private AuthSpecs() {
    }

    public static RequestSpecification createAuthRequest(String username, String password) {

        AuthUser authUser = AuthUser.builder().username(username).password(password).build();

        return new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBody(authUser).
                build();
    }
}
