package businessentitiesapi;

import java.util.List;

public interface TicketManager {

    Ticket createTicket(String flightSeatId, int cabinBaggage, int checkedBaggage, String mealId, String bookingId, boolean canceled, String passengerId, double pricePaid);

    Ticket add(Ticket t);

    List<Ticket> getTickets();

}
