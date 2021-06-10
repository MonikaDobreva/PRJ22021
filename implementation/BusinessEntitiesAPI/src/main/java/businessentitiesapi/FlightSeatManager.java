package businessentitiesapi;

import businessentitiesapi.exceptions.FlightStorageException;

import java.util.List;

public interface FlightSeatManager {

    FlightSeat createFlightSeat(int seatId, int flightId, boolean available);

    FlightSeat add(FlightSeat f) throws FlightStorageException;

    List<FlightSeat> getFlightSeats();

    FlightSeat getFromSeatId(int id);

    //List<FlightSeat> addAll(List<Integer> seatsId, int flightId);

    List<FlightSeat> getAvailableFlightSeats(Flight flight, String seatType);

}
