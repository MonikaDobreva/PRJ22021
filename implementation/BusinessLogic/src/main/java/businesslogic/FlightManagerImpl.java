package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import persistence.FlightStorageService;

import java.time.LocalDate;
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
    public Flight createFlight(String name, LocalDate departure, LocalDate arrival) {
        return new FlightImpl(name, departure, arrival);
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
