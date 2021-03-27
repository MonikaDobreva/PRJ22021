package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightImplTest {

    FlightManager fm = new FlightManagerImpl();
    Flight f = fm.createFlight(
            "LH388",
            LocalDateTime.parse("10:15 2007-12-03", DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
            LocalDateTime.parse("10:15 2008-12-03", DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
            "Boeing 747",
            "DUS",
            "ATX");

    // Tryout: ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]", new DateTimeFormatter("yyyy-MM-DD-HH-mm")),

    @Test
    public void testToString(){
        assertThat(f).hasToString("3. DECEMBER: \n" +
                "Airplane Boeing 747 on flight LH388 departing from DUS at 10:15\n" +
                "and going to ATX arriving at 10:15");
    }

    @Test
    public void testGetter(){
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(f.getName()).isEqualTo("LH388");
            s.assertThat(f.getDepartureTime()).isEqualTo("2007-12-03T10:15");
            s.assertThat(f.getArrivalTime()).isEqualTo("2008-12-03T10:15");
            s.assertThat(f.getAirplane()).isEqualTo("Boeing 747");
            s.assertThat(f.getStartAirport()).isEqualTo("DUS");
            s.assertThat(f.getDestAirport()).isEqualTo("ATX");
        });
    }
}
