package persistence;

import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import businessentitiesapi.FlightManager;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class PersistenceAPIImpl implements PersistenceAPI, PersistenceImplementationProvider {

    @Override
    public FlightStorageService getFlightStorageService(FlightManager flightManager){
        return new FlightStorageServiceImpl(flightManager);
    }

    @Override
    public AirplaneStorageService getAirplaneStorageService(AirplaneManager airplaneManager){
        return new AirplaneStorageServiceImpl(airplaneManager);
    }

    @Override
    public AirportStorageService getAirportStorageService(AirportManager airportManager){
        return new AirportStorageServiceImpl(airportManager);
    }

}
