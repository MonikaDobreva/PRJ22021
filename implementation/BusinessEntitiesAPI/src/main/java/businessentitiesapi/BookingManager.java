package businessentitiesapi;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingManager {

    Booking createBooking(int personId, int userId, LocalDateTime timeOfBooking);

    Booking add(Booking b);

    List<Booking> getBookings();

    List<Booking> getBookingsOfFlight(int i);

    Booking getBookingById(int currentFlight);
}
