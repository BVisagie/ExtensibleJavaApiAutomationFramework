package nl.kza.tests.restful_booker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.providers.base.Name;
import nl.kza.restful_booker.model.bookings.BookingDates;
import nl.kza.restful_booker.model.bookings.BookingDetails;
import nl.kza.restful_booker.model.bookings.BookingId;
import nl.kza.restful_booker.shared.Helpers;
import nl.kza.restful_booker.specifications.BookingSpecs;
import nl.kza.shared.DataGenerationHelpers;
import nl.kza.shared.SharedSpecifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static nl.kza.restful_booker.shared.RestfulBookerApiConstants.URI_PART_BOOKING;
import static nl.kza.shared.SharedConstants.SYSTEM_UNDER_TEST_RESTFUL_BOOKER;
import static nl.kza.shared.SharedConstants.TEST_TYPE_COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;

@Tag(SYSTEM_UNDER_TEST_RESTFUL_BOOKER)
@Tag(TEST_TYPE_COMPONENT)
@DisplayName(SYSTEM_UNDER_TEST_RESTFUL_BOOKER)
class BookingTests extends RestfulBookerBaseApi {

    @Test
    @DisplayName("An authenticated and interested party should be able to retrieve a list of all booking id's.")
    void getBookingIds() {

        Response bookingIdsResponse = RestAssured
                .given()
                .get(URI_PART_BOOKING)
                .then()
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(bookingIdsResponse.asString());
        List<BookingId> bookingIdList = jsonPath.getList("", BookingId.class);
        assertThat(bookingIdList).withFailMessage("Booking id list seems to be empty!").isNotEmpty();
    }

    @Test
    @DisplayName("An authenticated and interested party should be able to delete a booking that is currently in the system.")
    void deleteBooking() {

        String username = "admin";
        String password = "password123";

        String authToken = Helpers.createNewToken(username, password);
        List<BookingId> bookingIdList = Helpers.getListOfCurrentBookings();
        BookingId randomBookingIdForDeletion = bookingIdList.get(randomSeed.nextInt(bookingIdList.size()));

        RestAssured
                .given()
                .spec(BookingSpecs.requestSpecAuthWithBookingIdParam(authToken))
                .delete(URI_PART_BOOKING + "/" + randomBookingIdForDeletion.getBookingid())
                .then()
                .spec(SharedSpecifications.generalStatusCreatedResponseSpec(5000));

    }

    @Test
    @DisplayName("An authenticated should be able to add a new booking within the system.")
    void addBooking() {

        String username = "admin";
        String password = "password123";

        String authToken = Helpers.createNewToken(username, password);

        Name randomPerson = DataGenerationHelpers.getNewRandomPerson();

        String checkIn = ZonedDateTime.now().plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String checkOut = ZonedDateTime.now().plusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BookingDates bookingDates = BookingDates.builder().checkin(checkIn).checkout(checkOut).build();

        BookingDetails bookingDetails = BookingDetails.builder()
                .firstname(randomPerson.firstName())
                .lastname(randomPerson.lastName())
                .totalprice(DataGenerationHelpers.getRandomNumberInRange(1000, 3500))
                .depositpaid(randomSeed.nextBoolean())
                .bookingdates(bookingDates)
                .additionalneeds("None")
                .build();

        Response bookingCreationResponse = RestAssured
                .given()
                .spec(BookingSpecs.requestSpecAuthWithBookingDetails(authToken, bookingDetails))
                .post(URI_PART_BOOKING)
                .then()
                .extract()
                .response();

        JsonPath responseJsonPath = bookingCreationResponse.jsonPath();
        int bookingId = responseJsonPath.get("bookingid");
        assertThat(bookingId).withFailMessage("No booking id returned even though booking seems to be created!").isNotNull();

        String bookingResponseFirstName = responseJsonPath.get("booking.firstname");
        assertThat(bookingResponseFirstName).isEqualTo(bookingDetails.getFirstname());

        String bookingResponseCheckIn = responseJsonPath.get("booking.bookingdates.checkin");
        assertThat(bookingResponseCheckIn).isEqualTo(bookingDetails.getBookingdates().getCheckin());
        String bookingResponseCheckOut = responseJsonPath.get("booking.bookingdates.checkout");
        assertThat(bookingResponseCheckOut).isEqualTo(bookingDetails.getBookingdates().getCheckout());
    }

}
