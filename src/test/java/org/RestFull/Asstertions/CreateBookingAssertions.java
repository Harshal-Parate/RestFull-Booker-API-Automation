package org.RestFull.Asstertions;

import org.RestFull.Pojos.ResponsePOJO.CreateBooking.CreateBookingResponseRoot;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;


public class CreateBookingAssertions extends AbstractAssert<CreateBookingAssertions, CreateBookingResponseRoot> {

    SoftAssertions assertions = new SoftAssertions();

    public CreateBookingAssertions(CreateBookingResponseRoot CreateBookingResponseRoot) {
        super(CreateBookingResponseRoot, CreateBookingAssertions.class);
    }

    public static CreateBookingAssertions assertThat(CreateBookingResponseRoot response) {
        return new CreateBookingAssertions(response);
    }

    public CreateBookingAssertions hasName(String expectedName) {
        String actualName = actual.getBooking().getFirstname();
        Assertions.assertThat(actualName)
                .withFailMessage("Actual not equal to expected")
                .isEqualTo(expectedName);

        return this;
    }
}
