package org.RestFull.Modules.RequestFormationService;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestMakerService {

    public static Response post(RequestSpecification requestSpecification, String payload) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(payload)
                .when()
                .post();
    }


}
