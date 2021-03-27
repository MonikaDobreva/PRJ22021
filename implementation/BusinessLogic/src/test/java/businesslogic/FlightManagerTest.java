package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightManagerTest {

    FlightManager fm = new FlightManagerImpl();

    @Test
    public void storeFlightsInList() {

        Flight f = fm.createFlight
                (
                        "LH388",
                        LocalDateTime.parse("10:15 2007-12-03", DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                        LocalDateTime.parse("10:15 2008-12-03", DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                        "Boeing 747",
                        "DUS",
                        "ATX"
                );


        List<Flight> flights = new ArrayList<>(Arrays.asList(f));

        fm.add(f);

        assertThat(fm.getFlights()).containsSequence(flights);
    }
}
