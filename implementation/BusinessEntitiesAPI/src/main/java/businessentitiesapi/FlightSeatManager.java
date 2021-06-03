package businessentitiesapi;

import java.util.List;

public interface FlightSeatManager {

    FlightSeat createFlightSeat(String seatId, String flightId, boolean available);

    FlightSeat add(FlightSeat f);

    List<FlightSeat> getFlightSeats();

}
