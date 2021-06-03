package persistence;

import businessentitiesapi.FlightSeat;
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

    private final DAO<FlightSeat, Integer> flightSeatDao;

    public FlightSeatStorageServiceImpl() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        flightSeatDao = daof.createDao(FlightSeat.class);
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

}
