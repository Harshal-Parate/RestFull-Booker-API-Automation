package org.RestFull.Pojos.RequestPOJO.CreateBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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