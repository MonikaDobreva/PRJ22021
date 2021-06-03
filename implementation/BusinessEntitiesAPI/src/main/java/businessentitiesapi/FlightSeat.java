package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName(value = "flightSeatsView")
public class FlightSeat implements Serializable {

    @ID
    private final int flightSeatId;

    private final String seatId, flightId;
    private final boolean available;

    public FlightSeat(int flightSeatId, String seatId, String flightId, boolean available) {
        this.flightSeatId = flightSeatId;
        this.seatId = seatId;
        this.flightId = flightId;
        this.available = available;
    }

    public int getFlightSeatId() {
        return flightSeatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getFlightId() {
        return flightId;
    }

    public boolean isAvailable() {
        return available;
    }
}
