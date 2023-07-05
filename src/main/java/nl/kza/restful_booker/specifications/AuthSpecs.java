package nl.kza.restful_booker.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import nl.kza.restful_booker.model.AuthUser;

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
