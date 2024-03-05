package org.RestFull.Modules.UsageOfObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.RestFull.Pojos.RequestPOJO.CreateBooking.CreateBookingBookingDates;
import org.RestFull.Pojos.RequestPOJO.CreateBooking.CreateBookingRoot;

import java.io.File;
import java.io.IOException;
//import io.github.sskorol.data.JsonReader;

public class FromDataToObjects {


    // Not Scalable approach if object Mapper is used

    public static CreateBookingRoot getCreateBookingPayload(CreateBookingRoot data) {
        return CreateBookingRoot.builder()
                .setFirstname(data.getFirstname())
                .setLastname(data.getLastname())
                .setDepositpaid(data.isDepositpaid())
                .setTotalprice(data.getTotalprice())
                .setBookingdates(bookingDates(data.getBookingdates()))
                .setAdditionalneeds(data.getAdditionalneeds())
                .build();
    }

    public static CreateBookingBookingDates bookingDates(CreateBookingBookingDates data) {
        return CreateBookingBookingDates.builder()
                .setCheckin(data.getCheckin())
                .setCheckout(data.getCheckout())
                .build();
    }

    static ObjectMapper objectMapper = new ObjectMapper();

    public static CreateBookingRoot mapJsonFileToCreateBookingRoot(String filePath){
        CreateBookingRoot rootData = null;
        try {
            rootData = objectMapper.readValue(new File(filePath), CreateBookingRoot.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getCreateBookingPayload(rootData);
    }
}