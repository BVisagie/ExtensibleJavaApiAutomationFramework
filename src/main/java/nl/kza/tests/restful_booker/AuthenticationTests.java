package nl.kza.tests.restful_booker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nl.kza.restful_booker.specifications.AuthSpecs;
import nl.kza.shared.SharedSpecifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static nl.kza.restful_booker.shared.RestfulBookerApiConstants.URI_PART_AUTH;
import static nl.kza.shared.SharedConstants.SYSTEM_UNDER_TEST_RESTFUL_BOOKER;
import static nl.kza.shared.SharedConstants.TEST_TYPE_COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;

@Tag(SYSTEM_UNDER_TEST_RESTFUL_BOOKER)
@Tag(TEST_TYPE_COMPONENT)
@DisplayName(SYSTEM_UNDER_TEST_RESTFUL_BOOKER)
class AuthenticationTests extends RestfulBookerBaseApi {

    @Test
    @DisplayName("An interested party with valid credentials should be able retrieve a token from the restful-booker API.")
    void validAuthRequest() {

        String username = "admin";
        String password = "password123";

        Response authenticationResponse = RestAssured
                .given()
                .spec(AuthSpecs.createAuthRequest(username, password))
                .post(URI_PART_AUTH)
                .then()
                .spec(SharedSpecifications.generalStatusOkResponseSpec(1850))
                .extract()
                .response();

        JsonPath responseJsonPath = authenticationResponse.jsonPath();
        String tokenValue = responseJsonPath.get("token");
        assertThat(tokenValue).withFailMessage("No token field returned for the given username and password!").isNotEmpty();
    }

    @Test
    @DisplayName("An interested party with invalid credentials should not be able retrieve a token from the restful-booker API.")
    void invalidAuthRequest() {

        String username = "abc";
        String password = "pass123";

        Response authenticationResponse = RestAssured
                .given()
                .spec(AuthSpecs.createAuthRequest(username, password))
                .post(URI_PART_AUTH)
                .then()
                .spec(SharedSpecifications.generalStatusOkResponseSpec(1850))
                .extract()
                .response();

        JsonPath responseJsonPath = authenticationResponse.jsonPath();
        String responseReason = responseJsonPath.get("reason");
        assertThat(responseReason).isEqualTo("Bad credentials");
    }


}
