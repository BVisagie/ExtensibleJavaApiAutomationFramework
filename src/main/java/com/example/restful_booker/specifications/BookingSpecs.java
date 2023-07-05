package com.example.restful_booker.specifications;

import com.example.restful_booker.model.bookings.BookingDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BookingSpecs {
    private BookingSpecs() {
    }

    public static RequestSpecification requestSpecAuthWithBookingIdParam(String authToken) {

        return new RequestSpecBuilder().
                addHeader("Cookie", "token=" + authToken).
                build();
    }

    public static RequestSpecification requestSpecAuthWithBookingDetails(String authToken, BookingDetails bookingDetails) {

        return new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("Cookie", "token=" + authToken).
                setBody(bookingDetails).
                build();
    }
}
