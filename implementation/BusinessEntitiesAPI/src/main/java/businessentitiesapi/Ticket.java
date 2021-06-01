package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

@TableName(value = "ticketsView")
public class Ticket {
    @ID
    private final int ticketId;

    private final int flightSeatId, cabinBaggage, checkedBaggage, mealId, bookingId, passengerId;
    private final boolean canceled;
    private final double pricePaid;

    public Ticket(int ticketId, int flightSeatId, int cabinBaggage, int checkedBaggage, int mealId, int bookingId, int passengerId, boolean canceled, double pricePaid) {
        this.ticketId = ticketId;
        this.flightSeatId = flightSeatId;
        this.cabinBaggage = cabinBaggage;
        this.checkedBaggage = checkedBaggage;
        this.mealId = mealId;
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.canceled = canceled;
        this.pricePaid = pricePaid;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getFlightSeatId() {
        return flightSeatId;
    }

    public int getCabinBaggage() {
        return cabinBaggage;
    }

    public int getCheckedBaggage() {
        return checkedBaggage;
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

    public boolean isCanceled() {
        return canceled;
    }

    public double getPricePaid() {
        return pricePaid;
    }
}
