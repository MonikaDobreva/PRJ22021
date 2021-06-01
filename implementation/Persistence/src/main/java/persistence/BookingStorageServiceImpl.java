package persistence;

import businessentitiesapi.Booking;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookingStorageServiceImpl implements BookingStorageService {

    private final DAO<Booking, Integer> bookingDao;

    public BookingStorageServiceImpl() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        bookingDao = daof.createDao(Booking.class);
    }

    @Override
    public List<Booking> getAll() {
        try {
            Collection<Booking> all = bookingDao.getAll();
            bookingDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Booking add(Booking f) {
        Optional<Booking> storedBooking = Optional.empty();
        try {
            TransactionToken tok = bookingDao.startTransaction();
            storedBooking = bookingDao.save(f);
            tok.commit();
            bookingDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedBooking.get();
    }

}
