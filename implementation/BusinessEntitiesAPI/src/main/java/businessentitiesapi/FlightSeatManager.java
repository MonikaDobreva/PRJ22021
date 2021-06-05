package businessentitiesapi;

import java.util.List;

public interface FlightSeatManager {

    FlightSeat createFlightSeat(int seatId, int flightId, boolean available);

    FlightSeat add(FlightSeat f);

    void delete(FlightSeat flightSeat);

    List<FlightSeat> get();

    List<FlightSeat> getFlightSeats();

    List<FlightSeat> addAll(List<Integer> seatsId, int flightId);

}
