package persistence;

import businessentitiesapi.Passenger;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PassengerStorageServiceImpl implements PassengerStorageService {

    private final DAO<Passenger, Integer> passengerDao;

    public PassengerStorageServiceImpl() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        passengerDao = daof.createDao(Passenger.class);
    }

    @Override
    public List<Passenger> getAll() {
        try {
            Collection<Passenger> all = passengerDao.getAll();
            passengerDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Passenger add(Passenger f) {
        Optional<Passenger> storedPassenger = Optional.empty();
        try {
            TransactionToken tok = passengerDao.startTransaction();
            storedPassenger = passengerDao.save(f);
            tok.commit();
            passengerDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedPassenger.get();
    }

}

