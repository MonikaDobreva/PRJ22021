package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightStorageServiceImpl implements FlightStorageService {

    private final FlightManager flightManager; //not used yet
    private List<Flight> flights; //data handling only through lists so far, DDB later

    public FlightStorageServiceImpl(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    @Override
    public void add(Flight f) {
        flights.add(f);
    }

    @Override
    public List<Flight> getAll() {
        return flights;
        // create dummy entries here
    }
}
