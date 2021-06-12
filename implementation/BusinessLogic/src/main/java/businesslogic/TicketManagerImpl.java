package businesslogic;

import businessentitiesapi.Ticket;
import businessentitiesapi.TicketManager;
import genericdao.dao.DAOFactory;
import persistence.TicketStorageService;

import java.math.BigDecimal;
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
        return new Ticket(ticketId, flightSeatId, cabinBaggage, checkedBaggage, mealId, bookingId, passengerId, canceled, pricePaid);
    }

    @Override
    public Ticket add(Ticket t) {
        //return ticketStorageService.add(t);
        return daoF.createDao(Ticket.class).save(t).get();
    }

    @Override
    public List<Ticket> getTickets() {
        return daoF.createDao(Ticket.class).getAll();
    }

//    @Override
//    public BigDecimal getSumOfTicketPrices() {
//        var allTickets = getTickets();
//        BigDecimal sum = BigDecimal.ZERO;
//        for (Ticket t : allTickets) {
//            sum = sum.add(t.getPricePaid());
//        }
//        return sum;
//    }

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

    @Override
    public BigDecimal getSumOfTicketPrices(List<Ticket> tickets) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Ticket t : tickets) {
            sum = sum.add(t.getPricePaid());
        }
        return sum;
    }

    @Override
    public int getCheckedBaggageAmount(List<Ticket> tickets) {
        int sum = 0;
        for (Ticket t : tickets) {
            sum = sum + t.getCheckedBaggage();
        }
        return sum;
    }

    @Override
    public int getCabinBaggageAmount(List<Ticket> tickets) {
        int sum = 0;
        for (Ticket t : tickets) {
            sum = sum + t.getCabinBaggage();
        }
        return sum;
    }

    @Override
    public List<Ticket> getTicketsOfBooking(int bookingID) {
        return daoF.createDao(Ticket.class).getByColumnValues("bookingId", bookingID);
    }

/*
    @Override
    public BigDecimal getSumOfTicketsOfLastSixMonth() {
        var today = LocalDateTime.now();
        var sixMAgo = today.minusMonths(6);
        String query =
                "select t.* " +
                "from tickets t " +
                "join bookings b on b.id = t.booking_id " +
                "where time_of_booking <= ? and time_of_booking >= ?;";
        var tickets = daoF.createDao(Ticket.class).anyQuery(query, today, sixMAgo);
        BigDecimal sum = BigDecimal.ZERO;
        for (Ticket t : tickets) {
            sum = sum.add(t.getPricePaid());
        }
        return sum;
    }
*/
}
