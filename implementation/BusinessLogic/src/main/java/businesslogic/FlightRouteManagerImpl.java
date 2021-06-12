package businesslogic;

import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightRouteManager;
import genericdao.dao.DAOFactory;
import persistence.FlightRouteStorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightRouteManagerImpl implements FlightRouteManager {

    private FlightRouteStorageService flightRouteStorageService;
    private DAOFactory daof;

    public void setFlightRouteStorageService(FlightRouteStorageService flightRouteStorageService, DAOFactory daof) {
        this.flightRouteStorageService = flightRouteStorageService;
        this.daof = daof;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        daof = pgdFactory;
    }

    @Override
    public FlightRoute createFlightRoute(int flightRouteID, String originAirportCode, String destinationAirportCode) {
        return new FlightRoute(flightRouteID, originAirportCode, destinationAirportCode);
    }

    @Override
    public FlightRoute add(FlightRoute fr){
        try {
            return daof.createDao(FlightRoute.class).save(fr).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FlightRoute> getFlightRoutes() {
        try {
            return new ArrayList<>(daof.createDao(FlightRoute.class).getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void checkExistence(String originAirport, String destinationAirport) {
        var flightRoutes = this.getFlightRoutes();

        Optional<FlightRoute> flightRoute = flightRoutes.stream()
                .filter(fr -> fr.getOriginAirportCode().equals(originAirport) && fr.getDestinationAirportCode().equals(destinationAirport))
                .findAny();

        if(flightRoute.isEmpty()){
            this.add(this.createFlightRoute(0, originAirport, destinationAirport));
        }
    }

}
