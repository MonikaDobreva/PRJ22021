package persistence;

import businessentitiesapi.Ticket;
import businessentitiesapi.TicketManager;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TicketStorageServiceImpl implements TicketStorageService {

    private final DAO<Ticket, Integer> ticketDao;

    public TicketStorageServiceImpl(TicketManager ticketManager) {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        ticketDao = daof.createDao(Ticket.class);
    }

    @Override
    public List<Ticket> getAll() {
        try {
            Collection<Ticket> all = ticketDao.getAll();
            ticketDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Ticket add(Ticket f) {
        Optional<Ticket> storedTicket = Optional.empty();
        try {
            TransactionToken tok = ticketDao.startTransaction();
            storedTicket = ticketDao.save(f);
            tok.commit();
            ticketDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedTicket.get();
    }

}
