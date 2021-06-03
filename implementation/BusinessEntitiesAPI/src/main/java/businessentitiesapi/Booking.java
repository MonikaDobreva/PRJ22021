package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "bookingsView")
public class Booking implements Serializable {

    @ID
    private final int bookingId;

    private final int personId;
    private final int userId;
    private final LocalDateTime timeOfBooking;

    public Booking(int bookingId, int personId, int userId, LocalDateTime timeOfBooking) {
        this.bookingId = bookingId;
        this.personId = personId;
        this.userId = userId;
        this.timeOfBooking = timeOfBooking;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getTimeOfBooking() {
        return timeOfBooking;
    }
}
