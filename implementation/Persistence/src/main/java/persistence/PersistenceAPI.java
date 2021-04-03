package persistence;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.AirportManager;
import businessentitiesapi.FlightManager;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface PersistenceAPI {

    /**
     * Get FlightStorageService
     * Provides a storage object that knows how to store and retrieve flights.
     * Implemented by a default method to enable the creation of customized
     * PersistenceFacade implementations with limited services, for test purposes.
     *
     * @param flightManager -
     * @return FlightStorageService object that knows how to store and retrieve flights.
     */
    default FlightStorageService getFlightStorageService(FlightManager flightManager){
        return null;
    }

    default AirportStorageService getAirportStorageService(AirportManager airportManager){
        return null;
    }

    default AirplaneStorageService getAirplaneStorageService(AirplaneManager airplaneManager){
        return null;
    }

    // This interface can be extended with all services that need to be made
    // available to the business logic

}
