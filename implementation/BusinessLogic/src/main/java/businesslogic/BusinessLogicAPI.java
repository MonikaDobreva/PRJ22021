package businesslogic;

import businessentitiesapi.AirplaneManager;
import businessentitiesapi.AirportManager;
import businessentitiesapi.FlightManager;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface BusinessLogicAPI {

    FlightManager getFlightManager();

    AirportManager getAirportManager();

    AirplaneManager getAirplaneManager();
    
    EditDetailsLogic getEditDetailsLogic();

}
