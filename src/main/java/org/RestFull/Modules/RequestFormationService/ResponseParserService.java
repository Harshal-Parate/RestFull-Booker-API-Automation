package org.RestFull.Modules.RequestFormationService;

import io.restassured.response.Response;

public class ResponseParserService {

    public static <T> T parsedResponse(Response response, Class<T> className) {
        return (T) response.as(className);
    }

    public static String parsedResponse(Response response) {
        return response
                .then()
                .extract()
                .response()
                .asString();
    }
}