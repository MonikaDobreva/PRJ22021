package persistence;

import businessentitiesapi.FlightManager;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class PersistenceAPIImpl implements PersistenceAPI, PersistenceImplementationProvider {

    @Override
    public FlightStorageService getFlightStorageService(FlightManager flightManager){
        return new FlightStorageServiceImpl(flightManager);
    }

}
