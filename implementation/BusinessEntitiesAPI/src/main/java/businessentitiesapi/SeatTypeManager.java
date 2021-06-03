package businessentitiesapi;

import java.util.List;

public interface SeatTypeManager {

    SeatType createSeatType(String name, double extraPrice);

    SeatType add(SeatType s);

    List<SeatType> getSeatTypes();
}
