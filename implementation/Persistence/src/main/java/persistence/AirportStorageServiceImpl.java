package persistence;

import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirportStorageServiceImpl implements AirportStorageService{

    //private static List<Airport> airports = new ArrayList<>();
    private final AirportManager airportManager;

    public AirportStorageServiceImpl(AirportManager airportManager) {
        this.airportManager = airportManager;
    }

    public Airport createAirportFromCSV(String[] a ) {
        return airportManager.createAirport(a[0], a[1], a[2], a[3]);
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
                    .map(this::createAirportFromCSV)
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

    public Airport getAirportByName(String iata){
        for (Airport a : getAll()) {
            return a.getIataCode().equals(iata) ? a : null;
        }
        return null;
    }
}
