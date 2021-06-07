package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import genericdao.dao.DAOFactory;

import persistence.FlightStorageService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
     * saves given flight f in the database.
     *
     * @param f Flight to be saved
     * @return true if sucessfull,false otherwise
     */
    @Override
    public Flight add(Flight f) {
//        flightStorageService.add(f);
        try {
            return daof.createDao(Flight.class).save(f).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flight> getFlights() {
        return flightStorageService.getAll();
    }

    /**
     * Method creates a fresh dao and calls the deleteEntity method from the
     * dao. This deletes given flight f in the database.
     *
     * @param f Flight to be deleted
     * @return true if sucessfull,false otherwise
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

    public int getLastID() {
        return flightStorageService.getLastID();
    }

    @Override
    public List<Flight> getFlightsByRouteId(int selectedRouteId) {
        return flightStorageService.getFlightsByRouteId(selectedRouteId);
    }

}
