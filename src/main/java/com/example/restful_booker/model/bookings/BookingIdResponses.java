package com.example.restful_booker.model.bookings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Data
public class BookingIdResponses {
    @JsonIgnore
    List<BookingId> bookingIdList;
}
