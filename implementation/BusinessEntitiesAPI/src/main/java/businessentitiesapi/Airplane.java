
package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

/**
 * This represents the airplanes in the company
 * @author Rachel
 */

@TableName("airplanesView")
public class Airplane implements Serializable {

    @ID
    private final int airplaneID;
    private final String airplaneCode;
    private final String model;
    private final int capacity;

    public Airplane(int airplaneID, String name, String code, int amountSeats) {
        this.airplaneID = airplaneID;
        this.airplaneCode = code;
        this.model = name;
        this.capacity = amountSeats;
    }
    /**
     * This should just return this Airplane object.
     * @return this object as Airplane type
     */
    public Airplane getAirplane() {
        return this;
    }

    /**
     * Get the ID of an airplane
     * @return airplaneID
     */
    public int getAirplaneID() {
        return airplaneID;
    }

    /**
     * Gives the code of the Airplane in the format A-BCDE
     * @return Airplane Code
     */
    public String getAirplaneCode() {
        return airplaneCode;
    }
    
    /**
     * Gives the Airplane name like "Boeing 377"
     * @return AirplaneName
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Gives the number of seats in this Airplane
     * @return seat amount
     */
    public int getCapacity() {
        return capacity;
    }
    //May expand by seat categories like business class or economy

    @Override
    public String toString() {
        return "Airplane with " + "code " + airplaneCode + " and name " + model + ", has a seat amount of " + capacity + '.';
    }

}
    
