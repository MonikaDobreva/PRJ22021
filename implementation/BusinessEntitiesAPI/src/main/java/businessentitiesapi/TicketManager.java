package businessentitiesapi;

import java.util.List;

public interface TicketManager {

    /**
     * Creates a new ticket entity
     * @param flightSeatId      The ID of the ticket
     * @param cabinBaggage      The amount of hand luggage
     * @param checkedBaggage    The amount of checked baggage
     * @param mealId            The type of meal ordered for the flight
     * @param bookingId         The ID of the booking belonging to this ticket
     * @param canceled          States if the respective passenger has canceled they visit on this plane
     * @param passengerId       The ID of the passenger using this ticket to board the plane
     * @param pricePaid         The price paid for this ticket
     * @return                  A new ticket object
     */
    Ticket createTicket(
            int flightSeatId,
            int cabinBaggage,
            int checkedBaggage,
            int mealId,
            int bookingId,
            boolean canceled,
            int passengerId,
            double pricePaid
    );

    /**
     * Adds a new ticket to the TicketManager, which stores all the ticket objects in a list
     *
     * @param t The ticket to be added
     * @return the added ticket
     */
    Ticket add(Ticket t);

    /**
     * Get all tickets which were previously added to the ticketStorage
     * @return a List of ticket objects
     */
    List<Ticket> getTickets();

}
