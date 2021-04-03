package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import businessentitiesapi.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.AirplaneStorageService;
import persistence.FlightStorageService;
import persistence.FlightStorageServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Mock
    FlightStorageService fStorage;

    Flight f1 = new FlightImpl("LH1234",
            LocalDateTime.of(2021, 12, 3, 10, 30),
            LocalDateTime.of(2021, 12, 3, 15, 30),
            "V-BBBB",
            "DUS",
            "YVY"
    );
    Flight f2 = new FlightImpl("LH4321",
            LocalDateTime.of(2021, 10, 5, 9, 45),
            LocalDateTime.of(2021, 10, 6, 6, 0),
            "A-RRRR",
            "DUS",
            "BCN"
    );

    List<Flight> flights;
    FlightManagerImpl flightManager = new FlightManagerImpl();

    @BeforeEach
    public void setupMock(){
        flights = new ArrayList<>();
        fStorage = mock(FlightStorageService.class);
        flightManager.setFlightStorageService(fStorage);
    }

    @ParameterizedTest
    @CsvSource( {
            "'AB1234', true",
            "'AB1', true",
            "'ab1234', false",
            "'AB12345', false",
            "'B12345', false",
            "'AB', false",
    } )
    void tFlightNameFormat (String flightName, boolean result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //FlightManagerImpl flightManager = new FlightManagerImpl();
        Class<?> c = FlightManagerImpl.class;
        Method privateStrMethod = c.getDeclaredMethod("checkFlightName",String.class);
        privateStrMethod.setAccessible(true);
        assertThat((boolean) privateStrMethod.invoke(null, flightName))
                .isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource( {
            "2021-12-03T10:15:30, true",
            "2007-12-03T10:15:30, false",
    } )
    void tFlightDate (LocalDateTime date, boolean result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //FlightManagerImpl flightManager = new FlightManagerImpl();
        Class<?> c = FlightManagerImpl.class;
        Method privateStrMethod = c.getDeclaredMethod("checkDate", LocalDateTime.class);
        privateStrMethod.setAccessible(true);
        assertThat((boolean) privateStrMethod.invoke(null, date))
                .isEqualTo(result);
    }

    @Test
    public void createFlightTest(){
        assertThat(flightManager.createFlight("LH4321",
                LocalDateTime.of(2021, 10, 5, 9, 45),
                LocalDateTime.of(2021, 10, 6, 6, 0),
                "A-RRRR",
                "DUS",
                "BCN").toString())
                .isEqualTo(f2.toString());
    }

    @Test
    public void addTest(){
//        TO DO
    }

    @Test
    public void getTest(){
        Mockito.when(fStorage.getAll()).thenReturn(flights);
        flights.add(f1);
        flights.add(f2);

        assertThat(flightManager.getFlights())
                .containsExactly(f1, f2);
    }

    /*
    FlightStorageService flightStorageService;
    BusinessLogicAPI businessLogicAPI;

    @Test
    public void storeFlightsInList() {

        FlightManager fm = businessLogicAPI.getFlightManager();

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

        flightStorageService.add(f);
        fm.add(f);

        assertThat(fm.getFlights()).containsSequence(flights);
    }
    */
}
