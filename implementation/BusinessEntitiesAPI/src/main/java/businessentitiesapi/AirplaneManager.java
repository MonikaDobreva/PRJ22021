
package businessentitiesapi;

import java.util.List;

/**
 *
 * @author Rachel
 */
public interface AirplaneManager {
    
    /**
     * Creates a new airplane, fed with the name, code and amount of seats.
     *
     * @param name like Boeing 377
     * @param code format A-BCDE
     * @param amountSeats total amount 
     * @return a new flight object
     */
    Airplane createAirplane(String name, String code, int amountSeats);

    /**
     * Adds a new airplane to the airplanManager, which stores all the airplane objects in a list
     *
     * @param f The airplane to be added
     */
    void add(Airplane f);

    /**
     * Get all airplane which were previously added to the airplaneStorage
     * @return a List of airplane objects
     */
    List<Airplane> getAirplanes();

    /**
     * Deletes the given airplane from the AirplaneManager
     */
    void delete(Airplane a);

}
