package businessentitiesapi;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Flight class, representing the flight of the airline company.
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
*/
public interface Flight {

    /**
     * Get the name of the flight
     * @return flight name
     */
    String getName();

    /**
     * Get the departure time of the flight
     * @return date and time of departure
     */
    LocalDateTime getDepartureTime();

    /**
     * Get the arrival time of the flight
     * @return date and time of departure
     */
    LocalDateTime getArrivalTime();

    /**
     * Get the Airplane object of a flight
     * For now only returns a string
     * @return name of the plane
     */
    Airplane getAirplane();

    /**
     * Get the starting airport of the flight
     * @return name of the starting airport
     */
    Airport getStartAirport();

    /**
     * Get the destination airport of the flight
     * @return name of the destination airport
     */
    Airport getDestAirport();
}