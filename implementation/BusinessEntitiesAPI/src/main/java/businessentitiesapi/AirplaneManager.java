
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

}
