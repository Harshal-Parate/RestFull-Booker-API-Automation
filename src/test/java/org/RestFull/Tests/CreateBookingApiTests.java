package org.RestFull.Tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.RestFull.Asstertions.CreateBookingAssertions;
import org.RestFull.Asstertions.ResponseLevelAssertions;
import org.RestFull.DataProviders.JsonDataProvider;
import org.RestFull.FilePaths.CreateBookingFilePaths;
import org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.BuildingDataDirectly;
import org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.Deserializer;
import org.RestFull.Modules.RequestFormationService.RequestMakerService;
import org.RestFull.Modules.RequestFormationService.RequestSpecificationService;
import org.RestFull.Modules.RequestFormationService.ResponseParserService;
import org.RestFull.Pojos.RequestPOJO.CreateBooking.CreateBookingRoot;
import org.RestFull.Pojos.ResponsePOJO.CreateBooking.CreateBookingResponseRoot;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.RestFull.DataProviders.DataProviderUtil.dataProviderCombiner;
import static org.RestFull.Endpoints.StringEndpoints.validCreateBookingEndpoint;
import static org.RestFull.Modules.DataBuilderUsingPojoAndDataProvider.Serializer.objectToString;
import static org.RestFull.Modules.FileReaders.ReadPropertiesFiles.getBaseUri;

public final class CreateBookingApiTests {

    private CreateBookingApiTests() {
    }


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

        //Custom response level assertions
        ResponseLevelAssertions.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);
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

        //Custom response level assertions
        ResponseLevelAssertions.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);
    }

/*
    @Test
    public void testingPayloadPassedUsingObjectMatcher() throws IOException {
        // *payload from file to pojos to object mapper to response method*

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);
        Response response = RequestMakerService
                .post(requestSpecification, mapJsonFileToCreateBookingRoot(CreateBookingFilePaths.getValidCreateBookingPayload()));
        CreateBookingResponseRoot parsedResponse = ResponseParserService
                .parsedResponse(response, CreateBookingResponseRoot.class);


        //Custom response level assertions
        ResponseLevelAssertions.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);

    }

 */

    @Test
    public void testingUsingDataByPojo() {

        RequestSpecification requestSpecification = RequestSpecificationService
                .requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);

        /*
        response 1 --> using post method created under request maker service,
        this post accepts the request Spec and class type payload.
        Second step --> pasring the response using the response pojos
        Third step -->can be asserted later if needed
         */

        Response response1 = RequestMakerService.post(requestSpecification, BuildingDataDirectly.payload());
        CreateBookingResponseRoot parsedResponse2 = ResponseParserService.parsedResponse(response1, CreateBookingResponseRoot.class);

        //Custom response level assertions
        ResponseLevelAssertions.assertThat(response1)
                .hasStatusCode(HttpStatus.SC_OK);


        /*
        Step 1 --> making payload using gson from the class objectToString
        Step 2 --> Creating response with the help of post method
        which in this case accepts req spec and payload in string
        Step 3 --> Parsing the response for step 2 into string
        Step 4 --> Converting it back to objects and mapping them to reponse pojo using GSON
         */
        String payLoad = objectToString(BuildingDataDirectly.payload());
        Response response = RequestMakerService.post(requestSpecification, payLoad);
        String parsedResponse = ResponseParserService.parsedResponse(response);
        CreateBookingResponseRoot createBookingResponseRoot = Deserializer.stringToObjects(parsedResponse, CreateBookingResponseRoot.class);

        //Custom response level assertions
        ResponseLevelAssertions.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);

    }

    @Test(dataProvider = "combinedDataProvider")
    @Description("Verify the response when valid data is passed")
    public void testingUsingDataProviders(CreateBookingRoot data, CreateBookingResponseRoot expectedResponse) {

        // Request Specification creation using abstracted method
        RequestSpecification requestSpecification =
                RequestSpecificationService.requestSpecification(getBaseUri("Beta"), validCreateBookingEndpoint);

        // Actual response creation
        Response response =
                RequestMakerService.post(requestSpecification, data);

        // Actual response parsing
        CreateBookingResponseRoot parsedResponseActual =
                ResponseParserService.parsedResponse(response, CreateBookingResponseRoot.class);


        // Asserting the Actual with the Expected --> both fetched from json files
        // Assert.assertEquals(parsedResponseActual.getBooking().getFirstname(), expectedResponse.getBooking().getFirstname());

        ResponseLevelAssertions.assertThat(response)
                .hasStatusCode(HttpStatus.SC_OK);

        CreateBookingAssertions.assertThat(parsedResponseActual)
                .hasName(expectedResponse.getBooking().getFirstname());

    }

    @Test
    public void loggingErrors() {

        Response response = RestAssured.given()
                .basePath("")
                .basePath("")
                .headers("", "")
                .filter(new ResponseLoggingFilter())
                .filter(new ErrorLoggingFilter())
                .when()
                .get();

        if (response.getStatusCode() >= 400) {
            System.out.println(
                    "Error is " + response.asString()
            );
        }

    }

    @Test
    public void extractTheFieldFromResponse() {

        String payload = "{\n" +
                "    \"firstname\" : \"Harhal\",\n" +
                "    \"lastname\" : \"Parate\",\n" +
                "    \"totalprice\" : 1000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        Response response = RestAssured.given()
                .basePath("/booking")
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .extract()
                .response();

        System.out.println(response.getCookies());

        Set<String> key = new HashSet<>(response.getCookies().keySet());
        List<String> values = new ArrayList<>(response.getCookies().values());

        Iterator<String> iterator = key.iterator();
        while (iterator.hasNext()) {
            String cookie = iterator.next();
            System.out.println(cookie);
        }

        for (int i = 0; i < values.size(); i++) {
            System.out.println(values.get(i));
        }
        /*

        {
            "bookingid": 2841,
            "booking": {
                "firstname": "Harshal",
                "lastname": "Parate",
                "totalprice": 1000,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
            },
                "additionalneeds": "Breakfast"
        }

         */

        JsonObject jsonObject = JsonParser.parseString(response.asString()).getAsJsonObject();
        String name = jsonObject.getAsJsonObject("booking").get("firstname").getAsString();
        String date = jsonObject.getAsJsonObject("booking").get("bookingdates").getAsJsonObject().get("checkin").getAsString();

        System.out.println(date);

        System.out.println(name);


    }


//    @DataProvider(name = "bookingPayload")
//    public Object[][] provideBookingData(){
//        return JsonDataProvider.provideData(CreateBookingFilePaths.getValidCreateBookingPayload(), CreateBookingRoot.class);
//    }
//
//    @DataProvider(name = "bookingResponse")
//    public Object[][] provideBookingResponse(){
//        return JsonDataProvider.provideData(CreateBookingFilePaths.getValidCreateBookingResponse(), CreateBookingResponseRoot.class);
//    }


    /*
    Data Provider ---> provides data on the basis of parameters provided into the @Test method,
    this is combine data provider which loads up two types of data
    1. payload to be sent --> to make request
    2. response from the JSON file --> to assert and compare

    the method --> dataProviderCombiner(Object[][], Object[][]) accepts the 2D arrays in argument and combine them in another 2D array
    to keep the size equal for the inputs, i used Math.min function this function make sure that the
    array in json files with min input should be considered---> check  "dataProviderCombiner" for more info
     */
    @DataProvider(name = "combinedDataProvider")
    public Object[][] provideCombinedData() {
        Object[][] payloadData = JsonDataProvider.provideData(CreateBookingFilePaths.getValidCreateBookingPayload(), CreateBookingRoot.class);
        Object[][] responseData = JsonDataProvider.provideData(CreateBookingFilePaths.getValidCreateBookingResponse(), CreateBookingResponseRoot.class);
        return dataProviderCombiner(payloadData, responseData);
    }


}