package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightSeat;

import java.util.List;

public interface FlightSeatStorageService {

    FlightSeat add(FlightSeat f);

    List<FlightSeat> getAll();

    List<FlightSeat> addAll(List<FlightSeat>flightSeats);

    List<FlightSeat> findAvailableSeatsForFlight(Flight flight, String seatType);

    void updateFlightSeatAvailability(boolean availability, int id);

}
