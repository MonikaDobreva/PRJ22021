package persistence;

import businessentitiesapi.Booking;
import businessentitiesapi.Flight;

import java.util.List;

public interface BookingStorageService {

    Booking add(Booking b);
    List<Booking> getAll();
    List<Booking> getBookingsOfFlight(int i);

}
