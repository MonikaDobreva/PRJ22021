package businesslogic;

import businessentitiesapi.Booking;
import businessentitiesapi.BookingManager;
import businessentitiesapi.Flight;
import persistence.BookingStorageService;
import persistence.FlightStorageService;

import java.time.LocalDateTime;
import java.util.List;

public class BookingManagerImpl implements BookingManager {

    private BookingStorageService bookingStorageService;

    public void setBookingStorageService(BookingStorageService bookingStorageService) {
        this.bookingStorageService = bookingStorageService;
    }

    @Override
    public Booking createBooking(String personId, String userId, LocalDateTime timeOfBooking) {
        //The 0 is just a placeholder
        return new Booking(0, personId, userId, timeOfBooking);
    }

    @Override
    public Booking add(Booking b) {
        return bookingStorageService.add(b);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingStorageService.getAll();
    }

}
