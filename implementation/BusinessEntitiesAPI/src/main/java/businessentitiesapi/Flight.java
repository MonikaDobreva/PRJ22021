package businessentitiesapi;


import java.time.LocalDate;

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
    LocalDate getDepartureTime();

    /**
     * Get the arrival time of the flight
     * @return date and time of departure
     */
    LocalDate getArrivalTime();
}