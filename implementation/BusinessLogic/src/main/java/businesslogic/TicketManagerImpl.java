package businesslogic;

import businessentitiesapi.Ticket;
import businessentitiesapi.TicketManager;
import genericdao.dao.DAOFactory;
import persistence.TicketStorageService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TicketManagerImpl implements TicketManager {




    private TicketStorageService ticketStorageService;
    private DAOFactory daoF;

    public void setTicketStorageService(TicketStorageService ticketStorageService, DAOFactory pgdFactory) {
        this.ticketStorageService = ticketStorageService;
        daoF = pgdFactory;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        daoF = pgdFactory;
    }

    @Override
    public Ticket createTicket(
            int ticketId,
            int flightSeatId,
            int cabinBaggage,
            int checkedBaggage,
            int mealId,
            int bookingId,
            boolean canceled,
            int passengerId,
            BigDecimal pricePaid
    ) {
        return new Ticket(ticketId, flightSeatId, cabinBaggage, checkedBaggage, mealId, bookingId, canceled, passengerId, pricePaid);
    }

    @Override
    public Ticket add(Ticket t) {
        //return ticketStorageService.add(t);
        try {
            return daoF.createDao(Ticket.class).save(t).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getTickets() {
        try {
            return new ArrayList<>(daoF.createDao(Ticket.class).getAll());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BigDecimal getSumOfTicketPrices() {
        var allTickets = getTickets();
        BigDecimal sum = BigDecimal.ZERO;
        for (Ticket t : allTickets) {
            sum = sum.add(t.getPricePaid());
        }
        return sum;
    }

    @Override
    public boolean delete(Ticket t) {
        try {
            daoF.createDao(Ticket.class).deleteEntity(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
