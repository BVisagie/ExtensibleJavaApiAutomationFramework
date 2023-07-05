package nl.kza.restful_booker.model.bookings;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class BookingDates {
    private String checkin;
    private String checkout;
}