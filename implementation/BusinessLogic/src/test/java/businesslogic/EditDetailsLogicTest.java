package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import businessentitiesapi.FlightRouteManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;

/**
 *
 * @author Rachel
 */
public class EditDetailsLogicTest {

    @Mock
    FlightManager fManagerM;
    @Mock
    FlightRouteManager fRouteManagerM;

    EditDetailsLogic el;
    Flight f;

    @BeforeEach
    public void setupT() {
        fManagerM = mock(FlightManager.class);
        fRouteManagerM = mock(FlightRouteManager.class);
        el = new EditDetailsLogic(fManagerM, fRouteManagerM);
        f = new Flight(5,
                "DUS", "YVY",
                LocalDateTime.of(2021, 12, 3, 10, 30),
                LocalDateTime.of(2021, 12, 3, 15, 30),
                "V-BBBB",
                new BigDecimal("100.50"));

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
        LocalDateTime expectedDate = LocalDateTime.parse(
                expected,
                DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
        assertThat(el.makeDateTime(hour, min, d)).isEqualTo(expectedDate);
    }

    /**
     * Flight is wrong, data rejected on the layer of the flightmanager map that
     * is returned should contain flightError
     */
    @Test
    public void passDataTest1() {
        Mockito.when(fManagerM.createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any())).thenThrow(RuntimeException.class);

        Map<String, String> savedValues = new HashMap<>();

        savedValues.put("cVArrHour", "12");
        savedValues.put("cVArrMin", "40");
        savedValues.put("cVArrDate", "1999-04-12");

        Map<String, String> returnValues = el.passData(savedValues, f);

        verify(fManagerM).createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any());
    
        assertThat(returnValues).containsKey("flightError");
    }
    
    /**
     * Flight is right, update method returns false, map that
     * is returned should contain updateError
     */
    @Test
    public void passDataTest2() {
        Mockito.when(fManagerM.createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any())).thenReturn(f);
        
        Mockito.when(fManagerM.update(f)).thenReturn(false);

        Map<String, String> savedValues = new HashMap<>();

        savedValues.put("cVArrHour", "12");
        savedValues.put("cVArrMin", "40");
        savedValues.put("cVArrDate", "2025-04-12");

        Map<String, String> returnValues = el.passData(savedValues, f);

        verify(fManagerM).createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any());
        verify(fManagerM).update(f);
    
        assertThat(returnValues).containsKey("updateError");
    }
    
    /**
     * Flight is right, update method returns true, map that
     * is returned should contain worked
     */
    @Test
    public void passDataTest3() {
        Mockito.when(fManagerM.createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any())).thenReturn(f);
        
        Mockito.when(fManagerM.update(f)).thenReturn(true);

        Map<String, String> savedValues = new HashMap<>();

        savedValues.put("cVArrHour", "12");
        savedValues.put("cVArrMin", "40");
        savedValues.put("cVArrDate", "2025-04-12");

        Map<String, String> returnValues = el.passData(savedValues, f);

        verify(fManagerM).createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any());
        verify(fManagerM).update(f);
        verify(fRouteManagerM).checkExistence(Mockito.anyString(), Mockito.anyString());
    
        assertThat(returnValues).containsKey("worked");
    }

   
}
