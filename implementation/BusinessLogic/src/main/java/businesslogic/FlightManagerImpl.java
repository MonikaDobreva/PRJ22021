package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import persistence.FlightStorageService;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightManagerImpl implements FlightManager {

    private FlightStorageService flightStorageService;

    public void setFlightStorageService(FlightStorageService flightStorageService){
        this.flightStorageService = flightStorageService;
    }


    @Override
    public Flight createFlight(String name,
                               ZonedDateTime depTime,
                               ZonedDateTime arrTime,
                               String airplane,
                               String startAirport,
                               String destAirport) {
        return new FlightImpl(name, depTime, arrTime, airplane, startAirport, destAirport);
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
}
