package businessentitiesapi;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingManager {

    Booking createBooking(String personId, String userId, LocalDateTime timeOfBooking);

    Booking add(Booking b);

    List<Booking> getBookings();

}
