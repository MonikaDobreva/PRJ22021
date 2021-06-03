package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.Airport;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import persistence.FlightStorageService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Predicate;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightManagerImpl implements FlightManager {

    private FlightStorageService flightStorageService;

    public void setFlightStorageService(FlightStorageService flightStorageService) {
        this.flightStorageService = flightStorageService;
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
    }

    @Override
    public boolean update(Flight f) {
       return flightStorageService.update(f);
    }

    public int getLastID() {
        return flightStorageService.getLastID();
    }

    @Override
    public List<Flight> specialQuery() {
        return null;
    }


}
