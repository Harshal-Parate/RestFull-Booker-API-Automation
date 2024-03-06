package org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider;

import org.RestFull.Pojos.RequestPOJO.CreateBooking.CreateBookingBookingDates;
import org.RestFull.Pojos.RequestPOJO.CreateBooking.CreateBookingRoot;

public class BuildingDataDirectly {

    //  hard coding the data

    public static CreateBookingRoot payload() {

        return CreateBookingRoot.builder()
                .setFirstname("Harshal")
                .setLastname("Parate")
                .setBookingdates(innerProperties())
                .setDepositpaid(true)
                .setAdditionalneeds("Chicken")
                .setTotalprice(500)
                .build();
    }

    public static CreateBookingBookingDates innerProperties() {
        return CreateBookingBookingDates.builder()
                .setCheckout("2023-01-01")
                .setCheckin("2024-01-01")
                .build();
    }



}
