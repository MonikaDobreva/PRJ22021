package persistence;

import businessentitiesapi.Booking;
import businessentitiesapi.BookingManager;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.*;

public class BookingStorageServiceImpl implements BookingStorageService {

    private final DAO<Booking, Integer> bookingDao;
    private BookingManager bookingManager;


    public BookingStorageServiceImpl(BookingManager bookingManager) {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        bookingDao = daof.createDao(Booking.class);
        this.bookingManager = bookingManager;
    }

    @Override
    public List<Booking> getAll() {

        try {
            TransactionToken tok = bookingDao.startTransaction();
            Collection<Booking> all = bookingDao.getAll();
            bookingDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Booking> getBookingsOfFlight(int flightID){
        List<Booking> allB = null;
        try{
            TransactionToken tok = bookingDao.startTransaction();
            allB = bookingDao.anyQuery(
                    "select b.id, b.person_id, b.user_id, b.time_of_booking " +
                            "from bookings b " +
                            "join tickets t on b.id = t.booking_id " +
                            "join flight_seats fs on t.flight_seat_id = fs.id " +
                            "join flights f on f.id = fs.flight_id " +
                            "where f.id = ? " +
                            "order by b.id;", flightID);
            tok.commit();
            bookingDao.close();
            return new ArrayList<>(allB);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong with that query");
        }
        return allB;
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
