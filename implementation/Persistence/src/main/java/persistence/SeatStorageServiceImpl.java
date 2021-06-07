package persistence;

import businessentitiesapi.*;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SeatStorageServiceImpl implements SeatStorageService {

    private final DAO<Seat, Integer> seatDao;

    public SeatStorageServiceImpl() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        seatDao = daof.createDao(Seat.class);
    }

    @Override
    public List<Seat> getAll() {
        try {
            TransactionToken tok = seatDao.startTransaction();
            Collection<Seat> all = seatDao.getAll();
            seatDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Seat> getSeatsOfAirplane(Airplane ap) {
        try{
            TransactionToken tok = seatDao.startTransaction();
            Collection<Seat> result = seatDao.anyQuery(
                    "select * from seatsView " +
                            "where seatsView.airplaneID = ? ",
                    ap.getAirplaneID());
            seatDao.close();
            return new ArrayList<>(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Seat add(Seat f) {
        Optional<Seat> storedSeat = Optional.empty();
        try {
            TransactionToken tok = seatDao.startTransaction();
            storedSeat = seatDao.save(f);
            tok.commit();
            seatDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedSeat.get();
    }


    @Override
    public Seat getSeatForFlightSeat(FlightSeat flightSeat) {
        return seatDao.get(flightSeat.getSeatId()).get();
    }


}

