package org.RestFull.Pojos.RequestPOJO.CreateBooking;

import lombok.*;

@Getter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingBookingDates {

    private String checkin;
    private String checkout;

}