package businessentitiesapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface FlightManager {

    /**
     * Creates a new flight, fed with the name, departure and arrival date.
     *
     * @param name         The name of the flight
     * @param depTime      The date and time of the departure of the flight
     * @param arrTime      The date and time of the arrival of the flight
     * @param airplane     The airplane used for the flight
     * @param startAirport The airport where the flight takes off
     * @param destAirport  The airport where the flight arrives
     * @return a new flight object
     */
    Flight createFlight(
            int flightID,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            int airplane,
             int flightRouteId,
            int basePrice
    );

    /**
     * Adds a new flight to the FlightManager, which stores all the flight objects in a list
     *
     * @param f The flight to be added
     * @return the added flight
     */
    Flight add(Flight f);

    /**
     * Get all flights which were previously added to the flightStorage
     * @return a List of flight objects
     */
    List<Flight> getFlights();

    /**
     * Deletes the given flight from the FlightManager
     * @param f Flight
     */
    void delete(Flight f);

}
