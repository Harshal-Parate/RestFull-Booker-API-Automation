package org.RestFull.Tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.RestFull.FilePaths.CreateBookingFilePaths;
import org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.BuildingDataDirectly;
import org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.Deserializer;
import org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.Serializer;
import org.RestFull.Modules.RequestFormationService.RequestMakerService;
import org.RestFull.Modules.RequestFormationService.RequestSpecificationService;
import org.RestFull.Modules.RequestFormationService.ResponseParserService;
import org.RestFull.Pojos.ResponsePOJO.CreateBooking.CreateBookingResponseRoot;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.RestFull.Endpoints.StringEndpoints.validCreateBookingEndpoint;
import static org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.Serializer.objectToString;
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
        Response response = RequestMakerService.post(requestSpecification, payload);
        CreateBookingResponseRoot parsedResponse = ResponseParserService.parsedResponse(response, CreateBookingResponseRoot.class);
        System.out.println(parsedResponse.getBooking().getFirstname());
    }


    @Test
    public void testingPayloadPassedUsingObjectMatcher() throws IOException {
        // *payload from file to pojos to object mapper to response method*

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);
        Response response = RequestMakerService
                .post(requestSpecification, mapJsonFileToCreateBookingRoot(CreateBookingFilePaths.getValidCreateBookingPayload()));
        CreateBookingResponseRoot parsedResponse = ResponseParserService
                .parsedResponse(response, CreateBookingResponseRoot.class);


        System.out.println(parsedResponse.getBooking().getFirstname());
    }

    @Test
    public void testingUsingDataByPojo() {

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);


        /*
        response 1 --> using post method created under request make service,
        this post accepts the request Spec and class type payload.
        Second step --> pasring the response using the response pojos
        Third step -->can be asserted later if needed
         */
        Response response1 = RequestMakerService.post(requestSpecification, BuildingDataDirectly.payload());
        CreateBookingResponseRoot parsedResponse2 = ResponseParserService.parsedResponse(response1,CreateBookingResponseRoot.class);
        System.out.println(parsedResponse2.getBooking().getFirstname());


        /*
        Step 1 --> making payload using gson from the classs objectToString
        Step 2 --> Creating response with the help of post method
        which in this case accepts req spec and payload in string
        Step 3 --> Parsing the response for step 2 into string
        Step 4 --> Converting it back to objects and mapping them to reponse pojo using GSON
         */
        String payLoad = objectToString(BuildingDataDirectly.payload());
        Response response = RequestMakerService.post(requestSpecification, payLoad);
        String parsedResponse = ResponseParserService.parsedResponse(response);
        CreateBookingResponseRoot createBookingResponseRoot = Deserializer.stringToObjects(parsedResponse, CreateBookingResponseRoot.class);
        System.out.println(createBookingResponseRoot.getBooking().getLastname());




    }


}