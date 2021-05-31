package persistence;

import businessentitiesapi.Flight;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface FlightStorageService {

    Flight add(Flight f);
    List<Flight> getAll();
    boolean delete(Flight f);
    int getLastID();

}
