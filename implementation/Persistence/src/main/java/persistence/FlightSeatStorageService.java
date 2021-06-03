package persistence;

import businessentitiesapi.FlightSeat;

import java.util.List;

public interface FlightSeatStorageService {

    FlightSeat add(FlightSeat f);
    List<FlightSeat> getAll();
}
