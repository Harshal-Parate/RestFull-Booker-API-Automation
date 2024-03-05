package org.RestFull.Modules.RequestFormationService;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestMakerService {


    // as a class
    public static <T>Response post(RequestSpecification requestSpecification, Class<T> payload) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(payload)
                .when()
                .post();
    }

    // as a string
    public static <T>Response post(RequestSpecification requestSpecification, String payload) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(payload)
                .when()
                .post();
    }

    // as a map/string/method
    public static <T>Response post(RequestSpecification requestSpecification, Object payload) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(payload)
                .when()
                .post();
    }


}
