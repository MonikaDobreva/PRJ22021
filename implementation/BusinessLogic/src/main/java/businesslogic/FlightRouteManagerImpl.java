package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightRouteManager;
import persistence.FlightRouteStorageService;
import persistence.FlightStorageService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void checkExistence(String originAirport, String destinationAirport) {
        var flightRoutes = this.getFlightRoutes();

        Optional<FlightRoute> flightRoute = this.getFlightRoutes().stream()
                .filter(fr -> fr.getOriginAirportCode().equals(originAirport) && fr.getDestinationAirportCode().equals(destinationAirport))
                .findAny();

        if(flightRoute.isEmpty()){
            this.add(this.createFlightRoute(0, originAirport, destinationAirport));
        }
    }

//    @Override
//    public List<Flight> getFlightsByRouteId(int routeID) {
//        return flightRouteStorageService.getFlightsByRouteId(routeID);
//    }
}
