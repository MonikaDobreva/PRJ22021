package persistence;

import businessentitiesapi.Seat;

import java.util.List;

public interface SeatStorageService {

    Seat add(Seat s);
    List<Seat> getAll();

}
