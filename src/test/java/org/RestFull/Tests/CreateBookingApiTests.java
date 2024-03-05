package org.RestFull.Tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.RestFull.FilePaths.CreateBookingFilePaths;
import org.RestFull.Modules.RequestFormationService.RequestMakerService;
import org.RestFull.Modules.RequestFormationService.RequestSpecificationService;
import org.RestFull.Modules.RequestFormationService.ResponseParserService;
import org.RestFull.Pojos.ResponsePOJO.CreateBooking.CreateBookingResponseRoot;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.RestFull.Endpoints.StringEndpoints.validCreateBookingEndpoint;
import static org.RestFull.Modules.UsageOfObjectMapper.FromDataToObjects.mapJsonFileToCreateBookingRoot;
import static org.RestFull.Modules.FileReaders.ReadPropertiesFiles.getBaseUri;

public final class CreateBookingApiTests {

    private CreateBookingApiTests() {}

    @Test
    public void testingStringPayloadPassing() {

        // payload as String
        String payload = "{\n" +
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

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);
        Response response = RequestMakerService.post(requestSpecification, payload);
        CreateBookingResponseRoot parsedResponse = ResponseParserService.parsedResponse(response, CreateBookingResponseRoot.class);
        System.out.println(parsedResponse.getBooking().getFirstname());
    }

    @Test
    public void testingMapPayloadPassing() {
        // payload a map

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("firstname", "Harshal");
        payload.put("lastname", "Parate");
        payload.put("totalprice", 1000);
        payload.put("depositpaid", true);

        Map<String, Object> bookingDates = new LinkedHashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);
        Response response = RequestMakerService.post(requestSpecification,payload);
        CreateBookingResponseRoot parsedResponse = ResponseParserService.parsedResponse(response, CreateBookingResponseRoot.class);
        System.out.println(parsedResponse.getBooking().getFirstname());
    }


    @Test
    public void testingPayloadPassedUsingObjectMatcher() throws IOException {
        // *payload from file to pojos to object mapper to response method*

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);
        Response response = RequestMakerService
                .post(requestSpecification,mapJsonFileToCreateBookingRoot(CreateBookingFilePaths.getValidCreateBookingPayload()));
        CreateBookingResponseRoot parsedResponse = ResponseParserService
                .parsedResponse(response, CreateBookingResponseRoot.class);


        System.out.println(parsedResponse.getBooking().getFirstname());
    }







}