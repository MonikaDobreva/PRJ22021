package businesslogic.bulkvalidator;

import java.util.Map;

/**
 * Exception that contains other exceptions.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class BulkException extends Exception {

    final Map<String, Throwable> causes;

    public BulkException(Map<String, Throwable> causes) {
        this.causes = causes;
    }

    public Map<String, Throwable> getCauses() {
        return Map.copyOf(causes);
    }
}
