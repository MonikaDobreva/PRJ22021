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

    private final AirportManager airportManager;

    public AirportStorageServiceImpl(AirportManager airportManager) {
        this.airportManager = airportManager;
    }


    @Override
    public void add(Airport a) {

    }

    @Override
    public List<Airport> getAll() {
        return null;
    }

    @Override
    public void delete(Airport a) {

    }
}
