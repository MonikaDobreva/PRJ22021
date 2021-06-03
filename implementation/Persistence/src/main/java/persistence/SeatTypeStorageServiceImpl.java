package persistence;

import businessentitiesapi.SeatType;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SeatTypeStorageServiceImpl implements SeatTypeStorageService {

    private final DAO<SeatType, Integer> seatTypeDao;

    public SeatTypeStorageServiceImpl() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        seatTypeDao = daof.createDao(SeatType.class);
    }

    @Override
    public List<SeatType> getAll() {
        try {
            TransactionToken tok = seatTypeDao.startTransaction();
            Collection<SeatType> all = seatTypeDao.getAll();
            seatTypeDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SeatType add(SeatType f) {
        Optional<SeatType> storedSeatType = Optional.empty();
        try {
            TransactionToken tok = seatTypeDao.startTransaction();
            storedSeatType = seatTypeDao.save(f);
            tok.commit();
            seatTypeDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedSeatType.get();
    }

}

