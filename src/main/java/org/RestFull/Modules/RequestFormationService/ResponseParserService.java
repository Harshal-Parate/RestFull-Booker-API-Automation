package org.RestFull.Modules.RequestFormationService;

import io.restassured.response.Response;

public class ResponseParserService {

    public static String parsedResponse(Response response) {

        return response.then()
                .extract()
                .response()
                .asString();

    }


}
