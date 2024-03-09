package org.RestFull.FilePaths;

import lombok.Getter;

public final class CreateBookingFilePaths {

    private CreateBookingFilePaths() {}

    @Getter
    public static String validCreateBookingPayload = "src/test/resources/TestPayloadFiles/CreateBookingData/valid-data.json";

    @Getter
    public static String invalidCreateBookingPayload = "";

    @Getter
    public static String validCreateBookingResponse = "src/test/resources/ResponseData/Create Booking Response/Response-data.json";

    @Getter
    public static String validCreateBookingResponseSchema = "src/test/resources/ResponseSchemas/CreateBookingResponseSchemas/create-booking-valid-response-schema.json";

    @Getter
    public static String invalidCreateBookingResponseSchema = "";

}