package businesslogic;

import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightRouteManager;
import persistence.FlightRouteStorageService;
import persistence.FlightStorageService;

import java.util.List;

public class FlightRouteManagerImpl implements FlightRouteManager {

    private FlightRouteStorageService flightRouteStorageService;

    public void setFlightRouteStorageService(FlightRouteStorageService lightRouteStorageService) {
        this.flightRouteStorageService = lightRouteStorageService;
    }

    @Override
    public FlightRoute createFlightRoute(int flightRouteID, String originAirportCode, String destinationAirportCode) {
        return new FlightRoute(flightRouteID, originAirportCode, destinationAirportCode);
    }

    @Override
    public FlightRoute add(FlightRoute fr) {
        flightRouteStorageService.add(fr);
        return fr;
    }

    @Override
    public List<FlightRoute> getFlightRoutes() {
        return flightRouteStorageService.getAll();
    }
}
