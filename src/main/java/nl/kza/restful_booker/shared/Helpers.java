package nl.kza.restful_booker.shared;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nl.kza.restful_booker.model.bookings.BookingId;
import nl.kza.restful_booker.specifications.AuthSpecs;
import nl.kza.shared.SharedSpecifications;

import java.util.List;

import static nl.kza.restful_booker.shared.RestfulBookerApiConstants.URI_PART_AUTH;
import static nl.kza.restful_booker.shared.RestfulBookerApiConstants.URI_PART_BOOKING;

public class Helpers {

    private Helpers() {
    }

    public static String createNewToken(String username, String password) {

        Response authenticationResponse = RestAssured
                .given()
                .spec(AuthSpecs.createAuthRequest(username, password))
                .post(URI_PART_AUTH)
                .then()
                .spec(SharedSpecifications.generalStatusOkResponseSpec(5000))
                .extract()
                .response();

        JsonPath responseJsonPath = authenticationResponse.jsonPath();
        return responseJsonPath.get("token");
    }

    public static List<BookingId> getListOfCurrentBookings() {
        Response bookingIdsResponse = RestAssured
                .given()
                .get(URI_PART_BOOKING)
                .then()
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(bookingIdsResponse.asString());
        return jsonPath.getList("", BookingId.class);
    }
}
