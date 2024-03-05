package org.RestFull.Pojos.RequestPOJO.CreateBooking;

import lombok.*;

@Getter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRoot {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private CreateBookingBookingDates bookingdates;
    private String additionalneeds;

}