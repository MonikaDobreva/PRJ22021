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
    public Flight createFlight(
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

    //Method is static because I want to call to it without need of an object
    private static boolean checkFlightName(String name){
        Predicate<String> check = n -> ((n.length() >= 3 && n.length() <= 6) &&
                n.matches("[A-Z]{2}[0-9]{1,4}"));

        return check.test(name);
    }

    private static boolean checkDate(LocalDateTime date){
        Predicate<LocalDateTime> check = d -> d.isAfter(LocalDateTime.now());

        return check.test(date);
    }

//    @Override
//    public DateTimeFormatter dftYMD() {
//        return DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
//    }

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
    
    
}
