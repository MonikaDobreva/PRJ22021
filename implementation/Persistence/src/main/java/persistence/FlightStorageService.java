package persistence;

import businessentitiesapi.Flight;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface FlightStorageService {

    void add(Flight f);
    List<Flight> getAll();

}
