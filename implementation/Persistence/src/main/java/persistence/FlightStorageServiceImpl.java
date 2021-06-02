package persistence;

import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import businessentitiesapi.FlightSeat;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;

import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightStorageServiceImpl implements FlightStorageService {

    private final FlightManager flightManager;
    private final DAO<Flight, Integer> flightDao;
    private final DataSource ds;

    public FlightStorageServiceImpl(FlightManager flightManager) {
        this.flightManager = flightManager;
        ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        flightDao = daof.createDao(Flight.class);
    }

    @Override
    public List<Flight> getAll() {
        try {
            TransactionToken tok = flightDao.startTransaction();
            Collection<Flight> all = flightDao.getAll();
            flightDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Flight add(Flight f) {
        //TODO: Check if added flight is the proper one;
        Optional<Flight> storedFlight = Optional.empty();
        try {
            TransactionToken tok = flightDao.startTransaction();
            storedFlight = flightDao.save(f);
            tok.commit();
            flightDao.close();
//            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedFlight.get();
    }

    @Override
    public boolean delete(Flight f) {
        try {
            TransactionToken tok = flightDao.startTransaction();
            flightDao.deleteEntity(f);
            if (flightDao.get(f.getFlightID()).isEmpty()) {
               tok.commit();
               flightDao.close();
               return true;
            }else{
                tok.rollback();
                flightDao.close();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Flight f) {
        try {
            TransactionToken tok = flightDao.startTransaction();
            flightDao.update(f);
            if (flightDao.get(f.getFlightID()).get().equals(f)) {
               tok.commit();
               flightDao.close();
               return true;
            }else{
                tok.rollback();
                flightDao.close();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getLastID() {
        int id = 0;
        try{
            TransactionToken tok = flightDao.startTransaction();
            id = flightDao.lastId();
            flightDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        };

        return id;
    }

    @Override
    public List<FlightSeat> getSeats() {
        return List.of(new FlightSeat(), new FlightSeat());
    }

}
