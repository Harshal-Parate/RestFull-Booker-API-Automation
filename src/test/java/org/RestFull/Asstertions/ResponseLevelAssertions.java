package org.RestFull.Asstertions;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class ResponseLevelAssertions extends AbstractAssert<ResponseLevelAssertions, Response> {

    SoftAssertions assertions = new SoftAssertions();

    private ResponseLevelAssertions(Response response) {
        super(response, ResponseLevelAssertions.class);
    }

    public static ResponseLevelAssertions assertThat(Response response) {
        return new ResponseLevelAssertions(response);
    }


    public ResponseLevelAssertions hasStatusCode(int expectedStatusCode) {
        int actualResponse = actual.getStatusCode();
        Assertions.assertThat(actualResponse)
                .withFailMessage(() -> "actual response '" + actualResponse + "' is not same as'"
                        + expectedStatusCode + "' expected response")
                .isEqualTo(expectedStatusCode);

        return this;
    }
}


