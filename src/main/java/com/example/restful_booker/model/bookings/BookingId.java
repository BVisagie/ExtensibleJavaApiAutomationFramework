package com.example.restful_booker.model.bookings;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class BookingId {
    private int bookingid;
}
