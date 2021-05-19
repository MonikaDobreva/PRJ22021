package businessentitiesapi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.ThrowableAssert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
                    "Boeing 375",
                    new BigDecimal("30.00"));
        };

        assertThatCode(code)
                .as("This is a working Flight.")
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({
        //id,originAirport,destAirport,year,month,day,hour1,hour2,min,airplaneName,price
        "1,KLEW,DUS,2022,5,23,14,15,40,Boeing 35,80.00",
        "1,KLE,DUSW,2022,5,23,14,15,40,Boeing 35,80.00",
        "1,KLE,KLE,2022,5,23,14,15,40,Boeing 35,80.00",
        "1,KLE,DUS,2019,5,23,14,15,40,Boeing 35,80.00",
        "1,KLE,DUS,2022,5,23,14,12,40,Boeing 35,80.00",
        "1,KLE,DUS,2022,5,23,14,15,40,Boeing@35,80.00",
        "1,KLE,DUS,2019,5,23,14,15,40,Boeing 35,-1.00",})
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
            String airplanName,
            String price) {

        ThrowingCallable code = () -> {
            Flight f = new Flight(
                    id,
                    orgAirp,
                    destAirp,
                    LocalDateTime.of(year, Month.of(month), month, hourOrg, min),
                    LocalDateTime.of(year, Month.of(month), month, hourDes, min),
                    airplanName,
                    new BigDecimal(price)
            );
        };

        assertThatThrownBy(code)
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

}
