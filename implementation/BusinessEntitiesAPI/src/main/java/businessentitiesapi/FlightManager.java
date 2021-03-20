package businessentitiesapi;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface FlightManager {

    /**
     * Creates a new flight, fed with the name, departure and arrival date.
     * @param name The name of the flight
     * @param departure The date and time of the departure of the flight
     * @param arrival The date and time of the arrival of the flight
     * @return a new flight object
     */
    Flight createFlight(String name, LocalDate departure, LocalDate arrival);

    /**
     * Adds a new flight to the FlightManager, which stores all the flight objects in a list
     * @param f The flight to be added
     * @return the added flight
     */
    Flight add(Flight f);

    List<Flight> getFlights();

}
