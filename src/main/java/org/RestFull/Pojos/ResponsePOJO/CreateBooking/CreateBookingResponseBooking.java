package org.RestFull.Pojos.ResponsePOJO.CreateBooking;

import lombok.Getter;

@Getter
public class CreateBookingResponseBooking {

    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public CreateBookingResponseBookingDates bookingdates;
    public String additionalneeds;

}