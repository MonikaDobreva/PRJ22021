package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName(value = "seatsView")
public class Seat implements Serializable {

    @ID
    private final int seatId;

    private final String seatTypeId, seatNumber, airplaneId;

    public Seat(int seatId, String seatTypeId, String seatNumber, String airplaneId) {
        this.seatId = seatId;
        this.seatTypeId = seatTypeId;
        this.seatNumber = seatNumber;
        this.airplaneId = airplaneId;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getSeatTypeId() {
        return seatTypeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getAirplaneId() {
        return airplaneId;
    }
}
