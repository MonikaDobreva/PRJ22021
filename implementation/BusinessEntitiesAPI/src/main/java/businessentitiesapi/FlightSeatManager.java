package businessentitiesapi;

import java.util.List;

public interface FlightSeatManager {

    FlightSeat createFlightSeat(int seatId, int flightId, boolean available);

    FlightSeat add(FlightSeat f);

    List<FlightSeat> getFlightSeats();

    FlightSeat getFromSeatId(int id);

    //List<FlightSeat> addAll(List<Integer> seatsId, int flightId);

    List<FlightSeat> getAvailableFlightSeats(Flight flight, String seatType);

}
