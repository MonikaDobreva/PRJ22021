package businesslogic.validators;

import businesslogic.bulkvalidator.BulkException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PassengerInfoValidatorTest {

    private final PassengerInfoValidator passengerInfoValidator = new PassengerInfoValidator();

    @ParameterizedTest
    @CsvSource({
            "firstName,Richard",
            "gender,male",
            "email,richard@abc.de",
            "birthday,1999-03-28",
            "passportNumber,X1234YZ69"
    })
    void testValidateInputWithCorrectInformation(String field, String input) {
        assertThatCode(() -> passengerInfoValidator
                .validateInput(Map.of(field, input)))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({
    //        "firstName,Null,Name should not be null",
            "firstName,Carl0s,Name should not contain digits",
            "firstName,X,Name should be at least two letters long",
            "gender,null,Value should not be null",
            "email,null,Email address should not be null",
            "email,email address@abc.de,Email address may not contain spaces",
            "email,email.abc.de,Email address needs to contain the @-symbol",
            "email,email@abcde,Email address needs to contain a dot",
            "birthday,2099-12-12,Birthday may not be today or in the future",
            "birthday,1888-12-12,Birthday may not be before the 21th century",
            "passportNumber,PPN45678910,Passport number needs to be 9 digits long",
            "passportNumber,PX1Z3EJ?E,Passport number may only contain A-Z and 0-9"
    })
    void testValidateInputWithIncorrectInformation(String field, String input, String errorMessage) {
        ThrowableAssert.ThrowingCallable throwingCallable = () -> passengerInfoValidator.
                validateInput(Map.of(field, input));
        var exceptions = catchThrowableOfType(throwingCallable, BulkException.class);
        assertThat(exceptions
                .getCauses()
                .values())
                .first().asString()
                .contains(errorMessage);
    }
}
