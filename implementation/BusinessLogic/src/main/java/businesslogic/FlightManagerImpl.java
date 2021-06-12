package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;

import genericdao.dao.DAOFactory;
import persistence.FlightStorageService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 * @author Rachel
 */
public class FlightManagerImpl implements FlightManager {

    private FlightStorageService flightStorageService;
    private DAOFactory daof;

    /**
     * This is temporary method to keep the old versions still working. Should
     * be removed if change is complete.
     *
     * @param flightStorageService
     * @param pgdFactory
     */
    public void setFlightStorageService(FlightStorageService flightStorageService, DAOFactory pgdFactory) {
        this.flightStorageService = flightStorageService;
        daof = pgdFactory;
    }

    /**
     * This is the new method that passes only the factory, also used in the
     * test.
     *
     * @param pgdFactory
     */
    public void setDaoFactory(DAOFactory pgdFactory) {
        daof = pgdFactory;
    }

    /**
     * Method calls the constructor from the flight class and passes all param
     * along. Flight class can throw exception if wrong data is passed.
     *
     * @param flightID
     * @param originAirport
     * @param destinationAirport
     * @param depTime
     * @param arrTime
     * @param airplane
     * @param basePrice
     * @return newly created Flight
     */
    @Override
    public Flight createFlight(
            int flightID,
            String originAirport,
            String destinationAirport,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            String airplane,
            BigDecimal basePrice
    ) {
        return new Flight(
                flightID,
                originAirport,
                destinationAirport,
                depTime,
                arrTime,
                airplane,
                basePrice);
    }

    /**
     * Method creates a fresh dao and calls the safe method from the dao. This
     * saves the given flight f in the database.
     *
     * @param f Flight to be saved
     * @return true if successful ,false otherwise
     */
    @Override
    public Flight add(Flight f) {
        try {
            return daof.createDao(Flight.class).save(f).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Method creates a fresh dao and calls the getAll method from the
     * dao. This returns all flights in the database.
     *
     * @return list of Flights if successful, null otherwise
     */
    @Override
    public List<Flight> getFlights() {
//        return flightStorageService.getAll();
        try {
            return new ArrayList<>(daof.createDao(Flight.class).getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method creates a fresh dao and calls the deleteEntity method from the
     * dao. This deletes given flight f in the database.
     *
     * @param f Flight to be deleted
     * @return true if successful,false otherwise
     */
    @Override
    public boolean delete(Flight f) {
//        return flightStorageService.delete(f);
        try {
            daof.createDao(Flight.class).deleteEntity(f);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method creates a fresh dao and calls the update method from the dao. This
     * updates given flight f in the database.
     *
     * @param f Flight to be updated
     * @return true if sucesfull,false otherwise
     */
    @Override
    public boolean update(Flight f) {
//       return flightStorageService.update(f);
        try {
            daof.createDao(Flight.class).update(f);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getLastID() {
        try {
            return daof.createDao(Flight.class).lastId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Flight> getFlightsByRouteId(int routeId) {
//        return flightStorageService.getFlightsByRouteId(selectedRouteId);
        try {
            String query =
                    "select f.* " +
                            "from flight_routes fr " +
                            "join flights f on fr.id = f.flight_route_id " +
                            "where fr.id = (?);";
            return new ArrayList<>(daof.createDao(Flight.class).anyQuery(query, routeId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long calcEST(LocalDateTime start, LocalDateTime end) {
        Duration d = Duration.between(start, end);
        return d.toMinutes();
    }

    @Override
    public Flight getLongestFlight() {
        var flights = getFlights();
        Flight lF = flights.get(0);
        for (Flight f : flights) {
            if (calcEST(f.getDepartureTime(), f.getArrivalTime()) > calcEST(lF.getDepartureTime(), lF.getArrivalTime())) {
                lF = f;
            }
        }
        return lF;
    }

}
