package org.RestFull.Pojos.RequestPOJO.CreateBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingBookingDates {

    private String checkin;
    private String checkout;

}