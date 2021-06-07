package businesslogic.bulkvalidator;

import java.util.function.BiFunction;

/**
 * A validator that checks and optionally converts a value, throwing an
 * exception when the validation fails.
 * <p>
 * The reason for this interface and its default method is to make in easy
 * to specify a map of validation rules.
 *
 * <pre class='brush:java'>
 *  Map.of(
 *          "customer.id", ( String s ) {@literal ->} Integer.valueOf( s ),
 *          "customer.someInt", validateWith( Integer::valueOf,
 *                                                    BusinessException::new,
 *                                                   "Invalid integer" ),
 *          "customer.birthDate", LocalDate::parse,
 *          "customer.postcode", BulkValidatorTest::validatePC
 *  );
 *
 * </pre>
 *
 * @param <R> result type
 * @param <X> thrown exception
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public interface Validator<R, X extends Throwable> {

    /**
     * Validate and convert String to value or throw an exception if invalid.
     *
     * @param input to validate
     * @return the converted value
     * @throws X on error
     */
    abstract R validate(String input) throws X;

    /**
     * Helper to create validator from conversion (which may throw an exception),
     * a supplier of the error message when the error occurs and a message used by the exception producing function.
     *
     * @param <B>        output type
     * @param <Y>        exception type
     * @param conversion to apply to go from String to B
     * @param exSupplier function that converts a string with the exception that occurs into a new RuntimeException.
     * @param message    the message to be added to the new exception.
     * @return a validator
     */
    static <B, Y extends Throwable> Validator<B, Y> validateWith(
            Validator<B, ? extends Throwable> conversion,
            BiFunction<String, Throwable, ? extends RuntimeException> exSupplier,
            String message) {

        return (t) -> {
            try {
                return conversion.validate(t);
            } catch (Throwable ex) {
                throw exSupplier.apply(message, ex.getCause());
            }
        };

    }
}
