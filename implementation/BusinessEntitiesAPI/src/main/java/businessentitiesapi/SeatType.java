package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName
public class SeatType implements Serializable {

    @ID
    private final int seatTypeId;

    private final String name;
    private final double extraPrice;

    public SeatType(int seatTypeId, String name, double extraPrice) {
        this.seatTypeId = seatTypeId;
        this.name = name;
        this.extraPrice = extraPrice;
    }

    public int getSeatTypeId() {
        return seatTypeId;
    }

    public String getName() {
        return name;
    }

    public double getExtraPrice() {
        return extraPrice;
    }
}
