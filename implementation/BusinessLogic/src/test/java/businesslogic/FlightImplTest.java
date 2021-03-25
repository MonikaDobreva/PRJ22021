package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightImplTest {

    FlightManager fm = new FlightManagerImpl();
    Flight f = fm.createFlight(
            "LH388",
            ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"),
            ZonedDateTime.parse("2008-12-03T10:15:30+01:00[Europe/Paris]"),
            "Boeing Whatnot",
            "DUS",
            "ATX");

    // Tryout: ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]", new DateTimeFormatter("yyyy-MM-DD-HH-mm")),

    @Test
    public void testToString(){
        assertThat(f).hasToString("Airplane Boeing WhatnotLH388 leaving at 2007-12-03T10:15:30+01:00[Europe/Paris] and arriving at 2008-12-03T10:15:30+01:00[Europe/Paris] going from DUS to ATX");
    }

    @Test
    public void testGetter(){
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(f.getName()).isEqualTo("LH388");
            s.assertThat(f.getDepartureTime()).isEqualTo("2007-12-03T10:15:30+01:00[Europe/Paris]");
            s.assertThat(f.getArrivalTime()).isEqualTo("2008-12-03T10:15:30+01:00[Europe/Paris]");
            s.assertThat(f.getAirplane()).isEqualTo("Boeing Whatnot");
            s.assertThat(f.getStartAirport()).isEqualTo("DUS");
            s.assertThat(f.getDestAirport()).isEqualTo("ATX");
        });
    }
}
