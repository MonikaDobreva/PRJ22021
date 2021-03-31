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
            Airplane airplane,
            Airport startAirport,
            Airport destAirport) {
        return new FlightImpl(name, depTime, arrTime, airplane, startAirport, destAirport);
    }

//    @Override
//    public DateTimeFormatter dftYMD() {
//        return DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
//    }

    @Override
    public void add(Flight f) {
        flightStorageService.add(f);
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
