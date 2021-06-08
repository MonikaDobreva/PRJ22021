package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName(value = "ticketsView")
public class Ticket implements Serializable {
    @ID
    private final int ticketId;

    private final int flightSeatId, mealId, bookingId, passengerId;
    private final int cabinBaggage, checkedBaggage;
    private final boolean canceled;
    private final BigDecimal pricePaid;


    public Ticket(int ticketId, int flightSeatId, int cabinBaggage, int checkedBaggage, int mealId, int bookingId, boolean canceled, int passengerId, BigDecimal pricePaid) {
        this.ticketId = ticketId;
        this.flightSeatId = flightSeatId;
        this.cabinBaggage = cabinBaggage;
        this.checkedBaggage = checkedBaggage;
        this.mealId = mealId;
        this.bookingId = bookingId;
        this.canceled = canceled;
        this.passengerId = passengerId;
        this.pricePaid = pricePaid;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getFlightSeatId() {
        return flightSeatId;
    }

    public int getMealId() {
        return mealId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getPassengerId() {
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

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", flightSeatId=" + flightSeatId +
                ", mealId=" + mealId +
                ", bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", cabinBaggage=" + cabinBaggage +
                ", checkedBaggage=" + checkedBaggage +
                ", canceled=" + canceled +
                ", pricePaid=" + pricePaid +
                '}';
    }
}
