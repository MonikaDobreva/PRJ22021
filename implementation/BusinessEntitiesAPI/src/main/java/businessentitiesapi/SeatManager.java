package businessentitiesapi;

import java.util.List;

public interface SeatManager {

    Seat createSeat(String seatTypeId, String seatNumber, String airplaneId);

    Seat add(Seat s);

    List<Seat> getSeats();

}
