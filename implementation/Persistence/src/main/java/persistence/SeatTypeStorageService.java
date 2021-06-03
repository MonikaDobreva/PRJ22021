package persistence;

import businessentitiesapi.Seat;
import businessentitiesapi.SeatType;

import java.util.List;

public interface SeatTypeStorageService {

    SeatType add(SeatType s);
    List<SeatType> getAll();

}
