package persistence;

import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
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
        //TODO: implement add flight;
        String getFlightRouteID = "SELECT * FROM getFlightRouteID(?, ?)";
        String getAirplaneID = "SELECT * FROM getAirplaneID(?)";
        try (Connection con = ds.getConnection();
             PreparedStatement pstm1 = con.prepareStatement(getFlightRouteID);
             PreparedStatement pstm2 = con.prepareStatement(getAirplaneID)) {

            pstm1.setString(1, f.getOriginAirport());
            pstm1.setString(2, f.getDestinationAirport());
            ResultSet rs1 = pstm1.executeQuery();

            int flightRouteID = rs1.getInt(1);

            pstm2.setString(1, f.getAirplane());
            ResultSet rs2 = pstm2.executeQuery();

            int airplaneID = rs2.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return f;
    }

    @Override
    public boolean delete(Flight f) {
        // TODO: implement flight deletion
        return false;
    }
}
