package businesslogic;

import businessentitiesapi.FlightManager;
import persistence.PersistenceAPI;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class BusinessLogicAPIImpl implements BusinessLogicImplementationProvider, BusinessLogicAPI{

    final PersistenceAPI persistenceAPI;

    BusinessLogicAPIImpl(PersistenceAPI persistenceAPI){
        this.persistenceAPI = persistenceAPI;
    }

    @Override
    public FlightManager getFlightManager() {
        FlightManagerImpl flightManager = new FlightManagerImpl();
        flightManager.setFlightStorageService(persistenceAPI.getFlightStorageService(flightManager));
        return flightManager;
    }
}
