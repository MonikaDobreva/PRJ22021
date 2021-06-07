package persistence;

import businessentitiesapi.Airplane;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.Seat;

import java.util.List;

public interface SeatStorageService {

    Seat add(Seat s);

    List<Seat> getAll();

   List<Seat> getSeatsOfAirplane(Airplane ap);

   Seat getSeatForFlightSeat(FlightSeat flightSeat);
}
