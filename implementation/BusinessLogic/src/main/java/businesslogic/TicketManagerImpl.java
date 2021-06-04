package businesslogic;

import businessentitiesapi.Ticket;
import businessentitiesapi.TicketManager;
import persistence.TicketStorageService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TicketManagerImpl implements TicketManager {

    private TicketStorageService ticketStorageService;

    public void setTicketStorageService(TicketStorageService ticketStorageService) {
        this.ticketStorageService = ticketStorageService;
    }


    @Override
    public Ticket createTicket(
            int flightSeatId,
            int cabinBaggage,
            int checkedBaggage,
            int mealId,
            int bookingId,
            boolean canceled,
            int passengerId,
            BigDecimal pricePaid
    ) {
        //The 0 is just a placeholder
        return new Ticket(0, flightSeatId, mealId, bookingId, passengerId, cabinBaggage, checkedBaggage, canceled, pricePaid);
    }

    @Override
    public Ticket add(Ticket t) {
        return ticketStorageService.add(t);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketStorageService.getAll();
    }
}
