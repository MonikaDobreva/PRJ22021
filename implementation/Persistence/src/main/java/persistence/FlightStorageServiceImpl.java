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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightStorageServiceImpl implements FlightStorageService {

    private final FlightManager flightManager;
    private static List<Flight> flights = new ArrayList<>(); //data handling only through lists so far, DDB later


    public FlightStorageServiceImpl(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    @Override
    public List<Flight> getAll() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        DAO<Flight, Integer> flightDao = daof.createDao(Flight.class);
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
    public void add(Flight f) {
        //flights.add(f);
//        try {
//            FileWriter writer = new FileWriter("flightStorage.csv", true);
//            String sb = "";
//            sb = sb.concat(f.getName() + ",");
//            sb = sb.concat(f.getDepartureTime() + ",");
//            sb = sb.concat(f.getArrivalTime() + ",");
//            sb = sb.concat(f.getAirplane() + ",");
//            sb = sb.concat(f.getStartAirport() + ",");
//            sb = sb.concat(f.getDestAirport() + "\n");
//            if (Files.lines(Path.of("flightStorage.csv")).count() == 0){
//                writer.write(sb);
//            } else {
//                writer.append(sb);
//            }
//            writer.close();
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
    }

//    @Override
//    public List<Flight> getAll() {
//        List<Flight> flights = new ArrayList<>();
//
//        try{
//            Files.lines(Path.of("flightStorage.csv"))
//                        .map(line -> line.split(","))
//                        .map(this::createFlight)
//                        .forEach(flights::add);
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//
//        return flights;
//    }

    @Override
    public void delete(Flight f) {
        // implement flight deletion
    }


//    private Flight createFlight( String[] s ) {
//        return this.flightManager.createFlight( s[0], LocalDateTime.parse(s[1]), LocalDateTime.parse(s[2]), s[3], s[4], s[5]);
//        //later with a database this will be replaced by a method, that fetches the needed object from another table
//        // by the use of the respective foreign key
//        //return null; //placeholder to satisfy the "add" controller method
//    }
}
