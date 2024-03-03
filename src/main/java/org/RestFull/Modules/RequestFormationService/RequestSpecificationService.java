package org.RestFull.Modules.RequestFormationService;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationService {


    public static RequestSpecification requestSpecification (String baseUri, String basePath) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .build();
    }
}