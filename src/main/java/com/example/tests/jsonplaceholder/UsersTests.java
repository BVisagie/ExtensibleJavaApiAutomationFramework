package com.example.tests.jsonplaceholder;

import com.example.jsonplaceholder.model.user.User;
import com.example.jsonplaceholder.shared.JsonPlaceholderApiConstants;
import com.example.shared.SharedConstants;
import com.example.shared.SharedSpecifications;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag(SharedConstants.SYSTEM_UNDER_TEST_JSON_PLACEHOLDER)
@Tag(SharedConstants.TEST_TYPE_COMPONENT)
@DisplayName(SharedConstants.SYSTEM_UNDER_TEST_JSON_PLACEHOLDER)
class UsersTests extends JsonPlaceholderBaseApi {

    @Test
    @DisplayName("When a list of all users is request a non empty list will be returned.")
    void getUsers() {

        Response usersResponse = RestAssured
                .given()
                .spec(SharedSpecifications.generalRequestSpecNoHeaderNoBody())
                .get(JsonPlaceholderApiConstants.URI_PART_USERS)
                .then()
                .spec(SharedSpecifications.generalStatusOkOnlyResponseSpec(1500))
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

