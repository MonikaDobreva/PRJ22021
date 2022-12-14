package businessentitiesapi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.ThrowableAssert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.assertj.core.api.SoftAssertions;

/**
 *
 * @author Rachel
 */
public class FlightTest {

    @Test
    public void constructorWorksTest() {

        ThrowingCallable code = () -> {
            Flight f = new Flight(
                    1,
                    "DUS",
                    "LOD",
                    LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                    LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                    "A-AAAA",
                    new BigDecimal("30.00"));
        };

        assertThatCode(code)
                .as("This is a working Flight.")
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({
        //id,originAirport,destAirport,year,month,day,hour1,hour2,min,airplaneName,price
        "1,KLEW,DUS,2022,5,23,14,15,40,A-AAAA,80.00",
        "1,KLE,DUSW,2022,5,23,14,15,40,A-AAAA,80.00",
        "1,KLE,KLE,2022,5,23,14,15,40,A-AAAA,80.00",
        "1,KLE,DUS,2019,5,23,14,15,40,A-AAAA,80.00",
        "1,KLE,DUS,2022,5,23,14,12,40,A-AAAA,80.00",
        "1,KLE,DUS,2022,5,23,14,15,40,Boeing@35,80.00",
        "1,KLE,DUS,2019,5,23,14,15,40,A-AAAA,-1.00",})
    public void constructorNotWorkingTest(
            int id,
            String orgAirp,
            String destAirp,
            int year,
            int month,
            int day,
            int hourOrg,
            int hourDes,
            int min,
            String airplaneName,
            String price) {

        ThrowingCallable code = () -> {
            Flight f = new Flight(
                    id,
                    orgAirp,
                    destAirp,
                    LocalDateTime.of(year, Month.of(month), month, hourOrg, min),
                    LocalDateTime.of(year, Month.of(month), month, hourDes, min),
                    airplaneName,
                    new BigDecimal(price)
            );
        };

        assertThatThrownBy(code)
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getterTest() {
        Flight f = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(f.getOriginAirport()).isEqualTo("DUS");
        softly.assertThat(f.getDestinationAirport()).isEqualTo("LOD");
        softly.assertThat(f.getDepartureTime()).isEqualTo(LocalDateTime.of(2022, Month.MARCH, 3, 13, 40));
        softly.assertThat(f.getArrivalTime()).isEqualTo(LocalDateTime.of(2022, Month.MARCH, 3, 14, 40));
        softly.assertThat(f.getAirplane()).isEqualTo("A-AAAA");
        softly.assertThat(f.getBasePrice()).isEqualTo(new BigDecimal("30.00"));
        softly.assertThat(f.getFlightID()).isEqualTo(1);

        softly.assertAll();
    }

    @Test
    public void toStringTest() {
        Flight f = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        assertThat(f.toString()).contains(
                List.of("1", "DUS", "LOD", "A-AAAA", "3", "13:40", "14:40"));
    }

    @Test
    public void equalsHashTest() {
        Flight ref = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight equal = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight unequal1 = new Flight(
                2,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight unequal2 = new Flight(
                1,
                "LAX",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight unequal3 = new Flight(
                1,
                "DUS",
                "LAX",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight unequal4 = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.FEBRUARY, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight unequal5 = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2024, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("30.00"));

        Flight unequal6 = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AKLT",
                new BigDecimal("30.00"));

        Flight unequal7 = new Flight(
                1,
                "DUS",
                "LOD",
                LocalDateTime.of(2022, Month.MARCH, 3, 13, 40),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 40),
                "A-AAAA",
                new BigDecimal("60.00"));

        HelperEqualsHash.verifyEqualsAndHashCode(ref, equal,
                unequal1,
                unequal2,
                unequal3,
                unequal4,
                unequal5,
                unequal6,
                unequal7);
    }
}
