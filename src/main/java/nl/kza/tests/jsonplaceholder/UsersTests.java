package nl.kza.tests.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nl.kza.jsonplaceholder.model.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.kza.jsonplaceholder.shared.JsonPlaceholderApiConstants.URI_PART_USERS;
import static nl.kza.shared.SharedConstants.SYSTEM_UNDER_TEST_JSON_PLACEHOLDER;
import static nl.kza.shared.SharedConstants.TEST_TYPE_COMPONENT;
import static nl.kza.shared.SharedSpecifications.generalRequestSpecNoHeaderNoBody;
import static nl.kza.shared.SharedSpecifications.generalStatusOkOnlyResponseSpec;
import static org.assertj.core.api.Assertions.assertThat;

@Tag(SYSTEM_UNDER_TEST_JSON_PLACEHOLDER)
@Tag(TEST_TYPE_COMPONENT)
@DisplayName(SYSTEM_UNDER_TEST_JSON_PLACEHOLDER)
class UsersTests extends JsonPlaceholderBaseApi {

    @Test
    @DisplayName("When a list of all users is request a non empty list will be returned.")
    void getUsers() {

        Response usersResponse = RestAssured
                .given()
                .spec(generalRequestSpecNoHeaderNoBody())
                .get(URI_PART_USERS)
                .then()
                .spec(generalStatusOkOnlyResponseSpec(1500))
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(usersResponse.asString());
        List<User> usersList = jsonPath.getList("", User.class);
        assertThat(usersList).withFailMessage("Users list seems to be empty!").isNotEmpty();

        // Example, check some random fields within the list for every user
        for (User user : usersList) {
            assertThat(user.getAddress().getStreet()).withFailMessage("Username: " + user.getUsername() + " seem to have no street address!").isNotEmpty();
            assertThat(user.getCompany().getCatchPhrase()).withFailMessage("Username: " + user.getUsername() + " seem to have no company catchphrase saved!").isNotEmpty();
        }
    }
}

