package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.AirportManager;
import businessentitiesapi.FlightManager;
import persistence.PersistenceAPI;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */
public class BusinessLogicAPIImpl implements BusinessLogicImplementationProvider, BusinessLogicAPI {

    final PersistenceAPI persistenceAPI;

    BusinessLogicAPIImpl(PersistenceAPI persistenceAPI) {
        this.persistenceAPI = persistenceAPI;
    }

    @Override
    public FlightManager getFlightManager() {
        FlightManagerImpl flightManager = new FlightManagerImpl();
        flightManager.setFlightStorageService(persistenceAPI.getFlightStorageService(flightManager));
        return flightManager;
    }

    public AirportManager getAirportManager() {
        AirportManagerImpl airportManager = new AirportManagerImpl();
        airportManager.setAirportStorageService(persistenceAPI.getAirportStorageService(airportManager));
        return airportManager;
    }

    public AirplaneManager getAirplaneManager() {
        AirplaneManagerImpl airplaneManager = new AirplaneManagerImpl();
        airplaneManager.setAirplaneStorageService(persistenceAPI.getAirplaneStorageService(airplaneManager));
        return airplaneManager;
    }

    @Override
    public EditDetailsLogic getEditDetailsLogic() {
        EditDetailsLogic edl = new EditDetailsLogic();
        return edl;
    }

}
