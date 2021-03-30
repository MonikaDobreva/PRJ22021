package businesslogic;

import businessentitiesapi.Airplane;

/**
 * @author Rachel
 */
public class AirplaneImpl implements businessentitiesapi.Airplane {

    private final String code;
    private final String name;
    private final int seatAmount;

    public AirplaneImpl(String name, String code, int amountSeats) {
        if (!name.equals("") && !code.equals("") && amountSeats != 0) {
            this.name = name;
            this.code = code;
            this.seatAmount = amountSeats;
        }else{
            //incorect input
            this.name = "Not right input";
            this.code = "X-XXXX";
            this.seatAmount = -1;
        }

    }

    @Override
    public Airplane getAirplane() {
        return this;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSeatAmount() {
        return seatAmount;
    }

    @Override
    public String toString() {
        return "Airplane with " + "code " + code + " and name " + name + ", has a seat amount of" + seatAmount + '.';
    }

}
