package org.RestFull.Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.RestFull.Modules.RequestFormationService.RequestMakerService;
import org.RestFull.Modules.RequestFormationService.RequestSpecificationService;
import org.RestFull.Modules.RequestFormationService.ResponseParserService;
import org.testng.annotations.Test;

import static org.RestFull.Endpoints.StringEndpoints.validCreateBookingEndpoint;
import static org.RestFull.Modules.FileReaders.ReadFiles.getBaseUri;

public final class CreateBookingApiTests {

    private CreateBookingApiTests() {}

    @Test
    public void testingFrameWork() {


        String p = "{\n" +
                "    \"firstname\" : \"Harshal\",\n" +
                "    \"lastname\" : \"Parate\",\n" +
                "    \"totalprice\" : 1000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification requestSpecification = RequestSpecificationService.requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);
        Response response = RequestMakerService.post(requestSpecification, p);
        String parsedResponse = ResponseParserService.parsedResponse(response);
        System.out.println(parsedResponse);




    }





}
