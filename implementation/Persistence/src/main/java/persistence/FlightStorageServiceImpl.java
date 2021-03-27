package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightStorageServiceImpl implements FlightStorageService {

    private final FlightManager flightManager; //not used yet
    private static List<Flight> flights = new ArrayList<>(); //data handling only through lists so far, DDB later

    public FlightStorageServiceImpl(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    @Override
    public void add(Flight f) {
        flights.add(f);
        System.out.println(flights);
    }

    @Override
    public List<Flight> getAll() {
        return flights;
//        Flight f1 = flightManager.createFlight("LH388", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
//        Flight f2 = flightManager.createFlight("LH388", LocalDate.parse("2020-01-04"), LocalDate.parse("2020-01-05"));
//        return new ArrayList<>(Arrays.asList(f1, f2));
    }
}
