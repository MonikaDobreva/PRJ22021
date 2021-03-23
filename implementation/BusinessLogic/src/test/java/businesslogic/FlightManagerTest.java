package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightManagerTest {

    FlightManager fm = new FlightManagerImpl();

    @Test
    public void storeFlightsInList(){

        Flight f1 = fm.createFlight("LH388", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));
        Flight f2 = fm.createFlight("LH388", LocalDate.parse("2020-01-04"), LocalDate.parse("2020-01-05"));

        List<Flight> flights = new ArrayList<>(Arrays.asList(f1, f2));

        fm.add(f1);
        fm.add(f2);

        assertThat(fm.getFlights()).containsSequence(flights);
    }
}
