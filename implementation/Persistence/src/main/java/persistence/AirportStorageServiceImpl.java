package persistence;

import businessentitiesapi.Airport;
import businessentitiesapi.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AirportStorageServiceImpl implements AirportStorageService{

    //private static List<Airport> airports = new ArrayList<>();

    public Airport createAirport( String[] a ) {
        return null;
    }


    @Override
    public void add(Airport a) {
        // add an Airport to the database
    }

    public List<Airport> getAll() {
        List<Airport> airports = new ArrayList<>();

        try {
            Files.lines(Path.of("airportStorage.csv"))
                    .map(line -> line.split(","))
                    .map(this::createAirport)
                    .forEach(airports::add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return airports; // to be continued
    }

    @Override
    public void delete(Airport a) {
        // delete an airport from the "database"
    }
}
