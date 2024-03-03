package org.RestFull.Endpoints;

import lombok.Getter;

public final class StringEndpoints {

    private StringEndpoints() {}

    @Getter
    public static String validCreateBookingEndpoint = "/booking";
    @Getter
    public static String invalidCreateBookingEndpoint = "/bookinggg";

}