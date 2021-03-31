package persistence;

import businessentitiesapi.Airport;
import businessentitiesapi.Flight;

import java.util.List;

public interface AirportStorageService {

    void add(Airport a);
    List<Airport> getAll();
    void delete(Airport a);

}
