package persistence;

import businessentitiesapi.Passenger;

import java.util.List;

public interface PassengerStorageService {

    Passenger add(Passenger p);
    List<Passenger> getAll();
}
