package businesslogic.bulkvalidator;

/**
 * Business specific exception example that needs message and cause.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
