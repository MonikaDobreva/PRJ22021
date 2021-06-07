package businesslogic.validators;

import businesslogic.bulkvalidator.BulkException;
import businesslogic.bulkvalidator.BulkValidator;
import businesslogic.bulkvalidator.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassengerInfoValidator {

    private final BulkValidator bulkValidator = new BulkValidator().addValidations(initMap());
    private static final String PASSPORT_ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private Map<String, Validator<?, ?>> initMap() {
        Map<String, Validator<?, ?>> passengerInfoMap = Map.of(
                "firstName", this::validateName,
                "lastName", this::validateName,
                "email", this::validateEmail,
                "birthday", this::validateBirthday,
                "passportNumber", this::validatePassportNumber
        );
        var otherFields = List.of("gender", "seatType", "seatNumber", "cabinLuggage", "handLuggage", "meal");
        Map<String, Validator<?, ?>> mutableMap = otherFields.stream()
                .collect(Collectors.toMap(i -> i, x -> this::validateCombobox));
        passengerInfoMap.forEach(mutableMap::put);
        return mutableMap;
    }

    private String validateName(String name) {
        if (name.equals("null")) {
            throw new PassengerInfoException("Name should not be null");
        }
        if (name.chars().anyMatch(Character::isDigit)) {
            throw new PassengerInfoException("Name should not contain digits");
        }
        if (name.length() < 2) {
            throw new PassengerInfoException("Name should be at least two letters long");
        }

        return name;
    }

    private String validateCombobox(String s) {
        if (s.equals("null")) {
            throw new PassengerInfoException("Value should not be null");
        }

        return s;
    }

    private String validateEmail(String s) {
        return null;
    }

    private String validateBirthday(String s) {
        return null;
    }

    private String validatePassportNumber(String s) {
        s = s.toUpperCase(Locale.ROOT);
        if (s.length() != 9) {
            throw new PassengerInfoException("Passport number needs to be 9 digits long");
        }
        //For every character check if the character is within PASSPORT_ALLOWED
        if (s.chars().anyMatch(x -> PASSPORT_ALLOWED.indexOf(x) == -1)) {
            throw new PassengerInfoException("Passport number may only contain A-Z and 0-9");
        }
        return s;
    }

    public Map<String, Object> validateInput(Map<String, String> userInput) throws BulkException {
        return bulkValidator.validate(userInput);
    }
}
