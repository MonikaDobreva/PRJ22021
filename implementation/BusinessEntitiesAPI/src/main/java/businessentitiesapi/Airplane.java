
package businessentitiesapi;

/**
 * This represents the airplanes in the company
 * @author Rachel
 */
public interface Airplane {
    
    /**
     * This should just return this Airplane object.
     * @return this object as Airplane type
     */
    Airplane getAirplane();
    
    /**
     * Gives the code of the Airplane in the format A-BCDE
     * @return Airplane Code
     */
    String getCode();
    
    /**
     * Gives the Airplane name like Boeing 377
     * @return AirplaneName
     */
    String getName();
    
    /**
     * Gives the number of seats in this Airplane
     * @return seat amount
     */
    int getSeatAmount();
    //May expand by seat categories like business class or economy
    
    
    
}
