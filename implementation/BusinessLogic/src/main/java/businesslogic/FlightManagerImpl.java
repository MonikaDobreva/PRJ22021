package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.Airport;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import persistence.FlightStorageService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
            String name,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            String airplane,
            String startAirport,
            String destAirport){
//            Airplane airplane,
//            Airport startAirport,
//            Airport destAirport) {
        return new FlightImpl(name, depTime, arrTime, airplane, startAirport, destAirport);
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
    public void delete(Flight f) {
        flightStorageService.delete(f);
    }
}
