package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.FlightSeatManager;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightSeatStorageServiceImpl implements FlightSeatStorageService {

    private final FlightSeatManager flightSeatManager;
    private final DAO<FlightSeat, Integer> flightSeatDao;

    public FlightSeatStorageServiceImpl(FlightSeatManager flightSeatManager) {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        flightSeatDao = daof.createDao(FlightSeat.class);
        this.flightSeatManager = flightSeatManager;
    }

    @Override
    public List<FlightSeat> getAll() {
        try {
            TransactionToken tok = flightSeatDao.startTransaction();
            Collection<FlightSeat> all = flightSeatDao.getAll();
            flightSeatDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<FlightSeat> addAll(List<FlightSeat> flightSeats) {
        try {
            TransactionToken tok = flightSeatDao.startTransaction();
            var all = flightSeatDao.saveAll(flightSeats);
            tok.commit();
            flightSeatDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public FlightSeat add(FlightSeat f) {
        Optional<FlightSeat> storedFlightSeat = Optional.empty();
        try {
            TransactionToken tok = flightSeatDao.startTransaction();
            storedFlightSeat = flightSeatDao.save(f);
            tok.commit();
            flightSeatDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedFlightSeat.get();
    }

    @Override
    public List<FlightSeat> findAvailableSeatsForFlight(Flight flight, String seatType) {
        var query = "SELECT *\n" +
                "FROM flightSeatsView\n" +
                "         JOIN seatsview s on flightseatsview.seatid = s.seatid\n" +
                "         JOIN seattypesview s2 on s.seattypeid = s2.seattypeid\n" +
                "WHERE flightId = ?\n" +
                "  AND available = true\n" +
                "  AND s2.name = ?;";
        return flightSeatDao.anyQuery(query, flight.getFlightID(), seatType);
    }

    @Override
    public void updateFlightSeatAvailability(boolean availability, int id) {
        var query = "UPDATE flightSeatsView SET available = ? WHERE flightSeatId = ? RETURNING *";
        flightSeatDao.anyQuery(query, availability, id);
    }

}
