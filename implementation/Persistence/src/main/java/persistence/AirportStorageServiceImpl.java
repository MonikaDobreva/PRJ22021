package persistence;

import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AirportStorageServiceImpl implements AirportStorageService{

    private final AirportManager airportManager;

    private final DAO<Airport, Integer> airportDao;
    private final DataSource ds;

    public AirportStorageServiceImpl(AirportManager airportManager) {
        this.airportManager = airportManager;
        ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        airportDao = daof.createDao(Airport.class);
    }

    @Override
    public void add(Airport a) {

    }

    @Override
    public List<Airport> getAll() {
        try {
            TransactionToken tok = airportDao.startTransaction();
            Collection<Airport> all = airportDao.getAll();
            airportDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Airport a) {

    }
}
