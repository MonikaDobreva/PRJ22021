package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName(value = "ticketsView")
public class Ticket implements Serializable {
    @ID
    private final int ticketId;

    private String flightSeatId, mealId, bookingId, passengerId;
    private final int cabinBaggage, checkedBaggage;
    private final boolean canceled;
    private final double pricePaid;


    public Ticket(int ticketId, String flightSeatId, String mealId, String bookingId, String passengerId, int cabinBaggage, int checkedBaggage, boolean canceled, double pricePaid) {
        this.ticketId = ticketId;
        this.flightSeatId = flightSeatId;
        this.mealId = mealId;
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.cabinBaggage = cabinBaggage;
        this.checkedBaggage = checkedBaggage;
        this.canceled = canceled;
        this.pricePaid = pricePaid;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getFlightSeatId() {
        return flightSeatId;
    }

    public String getMealId() {
        return mealId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public int getCabinBaggage() {
        return cabinBaggage;
    }

    public int getCheckedBaggage() {
        return checkedBaggage;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public double getPricePaid() {
        return pricePaid;
    }
}
