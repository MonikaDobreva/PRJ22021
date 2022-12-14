package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;
import java.util.Objects;

@TableName(value = "flightSeatsView")
public class FlightSeat implements Serializable {

    @ID
    private final int flightSeatId;
    private final int seatId, flightId;
    private final boolean available;

    public FlightSeat(int flightSeatId, int seatId, int flightId, boolean available) {
        this.flightSeatId = flightSeatId;
        this.seatId = seatId;
        this.flightId = flightId;
        this.available = available;
    }

    public int getFlightSeatId() {
        return flightSeatId;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getFlightId() {
        return flightId;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "FlightSeat " + flightSeatId +
                " with seatID " + seatId +
                " and flightID " + flightId ;
    }

}
