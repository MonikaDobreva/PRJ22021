package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;

import persistence.FlightStorageService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//import genericdao.dao.DAO;
//import genericdao.pgdao.PGDAOFactory;
//import genericdao.pgdao.PGJDBCUtils;
//import javax.sql.DataSource;


/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 * @author Rachel
 */

public class FlightManagerImpl implements FlightManager {

    private FlightStorageService flightStorageService;
//    private DAO<Flight, Integer> flightDao;
//    private DataSource ds;

    public void setFlightStorageService(FlightStorageService flightStorageService) {
        this.flightStorageService = flightStorageService;
//        ds = PGJDBCUtils.getDataSource("postgres");
//        PGDAOFactory daof = new PGDAOFactory(ds);
    }


    @Override
    public Flight createFlight (
            int flightID,
            String originAirport,
            String destinationAirport,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            String airplane,
            BigDecimal basePrice
    ){
        return new Flight(
                flightID,
                originAirport,
                destinationAirport,
                depTime,
                arrTime,
                airplane,
                basePrice);
    }

    @Override
    public Flight add(Flight f) {
        flightStorageService.add(f);
        return f;
    }

    @Override
    public List<Flight> getFlights() {
        return flightStorageService.getAll();
    }

    @Override
    public boolean delete(Flight f) {
        return flightStorageService.delete(f);
//        flightDao = daof.createDao(Flight.class);
//        try {
//            flightDao.deleteEntity(f);
//            flightDao.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override
    public boolean update(Flight f) {
       return flightStorageService.update(f);
    }

    public int getLastID() {
        return flightStorageService.getLastID();
    }

    @Override
    public List<Flight> getFlightsByRouteId(int selectedRouteId) {
        return flightStorageService.getFlightsByRouteId(selectedRouteId);
    }


}
