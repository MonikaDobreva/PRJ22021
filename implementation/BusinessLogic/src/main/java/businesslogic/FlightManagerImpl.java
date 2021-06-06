package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import genericdao.pgdao.PGDAOFactory;

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
   private PGDAOFactory daof;

    public void setFlightStorageService(FlightStorageService flightStorageService,PGDAOFactory pgdFactory) {
        this.flightStorageService = flightStorageService;
             daof = pgdFactory;
    }
    
//     public void setFlightStorageService(PGDAOFactory pgdFactory) {
//      daof = pgdFactory;
//    }

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
//        return flightStorageService.delete(f);
        try {
            daof.createDao(Flight.class).deleteEntity(f);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
