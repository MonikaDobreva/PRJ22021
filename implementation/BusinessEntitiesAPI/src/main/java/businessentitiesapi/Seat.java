package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName(value = "seatsView")
public class Seat implements Serializable {

    @ID
    private final int seatId;
    private final int seatTypeId;
    private final String seatNumber;
    private int airplaneId;

    public Seat(int seatId, int seatTypeId, String seatNumber, int airplaneId) {
        this.seatId = seatId;
        this.seatTypeId = seatTypeId;
        this.seatNumber = seatNumber;
        this.airplaneId = airplaneId;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getSeatTypeId() {
        return seatTypeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", seatTypeId=" + seatTypeId +
                ", seatNumber='" + seatNumber + '\'' +
                ", airplaneId=" + airplaneId +
                '}';
    }
}
