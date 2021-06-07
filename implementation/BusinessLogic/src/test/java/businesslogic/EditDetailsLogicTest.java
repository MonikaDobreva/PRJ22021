package businesslogic;

import businessentitiesapi.FlightManager;
import businessentitiesapi.FlightRouteManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Rachel
 */
public class EditDetailsLogicTest {

    @Mock
    FlightManager fManagerM;
    @Mock
    FlightRouteManager fRouteManagerM;

    @BeforeEach
    public void setupT() {
        fManagerM = mock(FlightManager.class);
        fRouteManagerM = mock(FlightRouteManager.class);
    }

    /**
     * This test is only for testing that a zero is appended at the beginning of
     * a single digit from hours and min.
     *
     * @param hour
     * @param min
     * @param d date
     * @param expected dateTime
     */
    @ParameterizedTest
    @CsvSource({
        //hour, min date
        "23,20,2021-04-18,23:20 2021-04-18",
        "0,1,2021-04-20,00:01 2021-04-20",
        "12,3,2021-07-10,12:03 2021-07-10"
    })
    public void makeDateTimeTest(String hour, String min, String d, String expected) {
        EditDetailsLogic el = new EditDetailsLogic(fManagerM,fRouteManagerM);
        
        
        LocalDateTime expectedDate = LocalDateTime.parse(
                expected,
                DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));

        assertThat(el.makeDateTime(hour, min, d)).isEqualTo(expectedDate);
    }
}
