package businesslogic;

import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightRouteManager;
import businessentitiesapi.exceptions.FlightStorageException;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
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
    public FlightRoute add(FlightRoute fr) throws FlightStorageException {
        try {
            DAO<FlightRoute, Integer> flightRouteDao = daof.createDao(FlightRoute.class);
            TransactionToken token = flightRouteDao.startTransaction();
            Optional<FlightRoute> storedFlightRoute = flightRouteDao.save(fr);
            if (fr.equals(storedFlightRoute.get())) {
                token.commit();
            } else {
                token.rollback();
                throw new Exception();
            }
            flightRouteDao.close();
            return storedFlightRoute.get();
        } catch (Exception e) {
            throw new FlightStorageException("Flight Route could not be added :(");
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
    public void checkExistence(String originAirport, String destinationAirport) throws FlightStorageException {
        var flightRoutes = this.getFlightRoutes();

        Optional<FlightRoute> flightRoute = this.getFlightRoutes().stream()
                .filter(fr -> fr.getOriginAirportCode().equals(originAirport) && fr.getDestinationAirportCode().equals(destinationAirport))
                .findAny();

        if(flightRoute.isEmpty()){
            this.add(this.createFlightRoute(0, originAirport, destinationAirport));
        }
    }

}
