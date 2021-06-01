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

public class FlightRouteStorageServiceImpl implements FlightRouteStorageService{
    private final FlightRouteManager flightRouteManager;

    private final DAO<FlightRoute, Integer> flightRouteDao;
    private final DataSource ds;

    public FlightRouteStorageServiceImpl(FlightRouteManager flightRouteManager) {
        this.flightRouteManager = flightRouteManager;
        ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        flightRouteDao = daof.createDao(FlightRoute.class);
    }

    @Override
    public FlightRoute add(FlightRoute fr) {
        Optional<FlightRoute> storedFlightRoute = Optional.empty();
        try {
            TransactionToken tok = flightRouteDao.startTransaction();
            storedFlightRoute = flightRouteDao.save(fr);
            tok.commit();
            flightRouteDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedFlightRoute.get();
    }

    @Override
    public List<FlightRoute> getAll() {
        try {
            TransactionToken tok = flightRouteDao.startTransaction();
            Collection<FlightRoute> all = flightRouteDao.getAll();
            flightRouteDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
