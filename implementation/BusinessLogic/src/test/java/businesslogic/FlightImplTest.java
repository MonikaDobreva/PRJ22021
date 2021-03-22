package businesslogic;

import businessentitiesapi.Flight;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightImplTest {

    Flight f = new FlightImpl("LH388", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"));

    @Test
    public void testToString(){
        assertThat(f).hasToString("LH388 leaving at the 2020-01-01 and arriving at the 2020-01-02");
    }

    @Test
    public void testGetter(){
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(f.getName()).isEqualTo("LH388");
            s.assertThat(f.getDepartureTime()).isEqualTo("2020-01-01");
            s.assertThat(f.getArrivalTime()).isEqualTo("2020-01-02");
        });

    }
}
