package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightStorageServiceImpl implements FlightStorageService {

    private final FlightManager flightManager; //not used yet
    private static List<Flight> flights = new ArrayList<>(); //data handling only through lists so far, DDB later


    public FlightStorageServiceImpl(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    @Override
    public void add(Flight f) {
        //flights.add(f);
        try {
            FileWriter writer = new FileWriter("flightStorage.csv", true);
            String sb = "";
            sb = sb.concat(f.getName() + ",");
            sb = sb.concat(f.getDepartureTime() + ",");
            sb = sb.concat(f.getArrivalTime() + ",");
            sb = sb.concat(f.getAirplane() + ",");
            sb = sb.concat(f.getStartAirport() + ",");
            sb = sb.concat(f.getDestAirport() + "\n");
            if (Files.lines(Path.of("flightStorage.csv")).count() == 0){
                writer.write(sb);
            } else {
                writer.append(sb);
            }
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Flight> getAll() {
        List<Flight> flights = new ArrayList<>();

        try{
            Files.lines(Path.of("flightStorage.csv"))
                        .map(line -> line.split(","))
                        .map(this::createFlight)
                        .forEach(flights::add);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return flights;
//        Flight f1 = flightManager.createFlight("LH388", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
//        Flight f2 = flightManager.createFlight("LH388", LocalDate.parse("2020-01-04"), LocalDate.parse("2020-01-05"));
//        return new ArrayList<>(Arrays.asList(f1, f2));
    }

    @Override
    public void delete(Flight f) {
        // implement flight deletion
    }


    private Flight createFlight( String[] s ) {
        return this.flightManager.createFlight( s[0], LocalDateTime.parse(s[1]), LocalDateTime.parse(s[2]), s[3], s[4], s[5]);
    }
}
