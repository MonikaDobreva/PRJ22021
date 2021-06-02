package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightSeat;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface FlightStorageService {

    Flight add(Flight f);
    List<Flight> getAll();
    boolean delete(Flight f);

    boolean update(Flight f);

    int getLastID();

    List<FlightSeat> getSeats();


}
