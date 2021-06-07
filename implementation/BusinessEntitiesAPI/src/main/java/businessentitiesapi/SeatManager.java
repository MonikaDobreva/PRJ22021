package businessentitiesapi;

import java.util.List;

public interface SeatManager {

    Seat createSeat(int seatTypeId, String seatNumber, int airplaneId);

    Seat add(Seat s);

    List<Seat> getSeats();

    List<Integer> getSeatIdsOfAirplane(Airplane a);

    Seat getSeatForFlightSeat(FlightSeat flightSeat);

}
