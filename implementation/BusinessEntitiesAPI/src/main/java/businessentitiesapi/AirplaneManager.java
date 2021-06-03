
package businessentitiesapi;

import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Rachel
 */
public interface AirplaneManager {
    
    /**
     * Creates a new airplane, fed with the name, code and amount of seats.
     *
     * @param airplaneID 
     * @param name like Boeing 377
     * @param code format A-BCDE
     * @param amountSeats total amount 
     * @return a new flight object
     */
    Airplane createAirplane(int airplaneID, String name, String code, int amountSeats);

    /**
     * Adds a new airplane to the airplaneManager, which stores all the airplane objects in a list
     *
     * @param a The airplane to be added
     */
    void add(Airplane a);

    /**
     * Return Airplane object matching the given Airplane Code
     *
     * @param airplaneCode The airplane Code from the Airplane to be returned
     * @return Airplane object
     */
    Airplane getAirplane(String airplaneCode);

    /**
     * Get all airplane which were previously added to the airplaneStorage
     * @return a List of airplane objects
     */
    List<Airplane> getAirplanes();

    /**
     * Deletes the given airplane from the AirplaneManager
     *
     * @param a Airplane to be deleted
     */
    void delete(Airplane a);

    /**
     * Check if Airplane is available for the given departure and arrival time
     *
     * @param a The airplane that we want to check availability
     * @param departure Departure time
     * @param arrival Arrival time
     */
    boolean checkAvailability(Airplane a, LocalDateTime departure, LocalDateTime arrival) throws IllegalArgumentException;

    /**
     * Get all the dates as a LocalDateTimeRange where an airplane has been scheduled
     *
     * @param a Airplane to obtain schedule
     *
     * @return a List of the date ranges when the airplane is not available
     */
    List<LocalDateTimeRange> airplaneSchedule (Airplane a);

}
