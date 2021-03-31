package persistence;

import businessentitiesapi.Airport;
import businessentitiesapi.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AirportStorageServiceImpl {

    private static List<Airport> airports = new ArrayList<>();

    private Airport createAirport(String[] s) {
        return null; // to be continued
    }


    public List<Airport> getAll() {
        List<Flight> flights = new ArrayList<>();

        try {
            Files.lines(Path.of("airportStorage.csv"))
                    .map(line -> line.split(","))
                    .map(this::createAirport)
                    .forEach(flights::add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null; // to be continued
    }
}
