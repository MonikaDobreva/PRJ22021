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
import java.math.BigDecimal;
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

    Flight f1 = new Flight(5,
            "DUS", "YVY",
            LocalDateTime.of(2021, 12, 3, 10, 30),
            LocalDateTime.of(2021, 12, 3, 15, 30),
            "V-BBBB",
            new BigDecimal("100.50"));

    Flight f2 = new Flight(1,
            "DUS", "BCN",
            LocalDateTime.of(2021, 10, 5, 9, 45),
            LocalDateTime.of(2021, 10, 6, 6, 0),
            "A-RRRR",
            new BigDecimal("200.00"));

    List<Flight> flights;
    FlightManagerImpl flightManager = new FlightManagerImpl();

    @BeforeEach
    public void setupMock(){
        flights = new ArrayList<>();
        fStorage = mock(FlightStorageService.class);
        flightManager.setFlightStorageService(fStorage);
    }

    @Test
    public void createFlightTest(){
        assertThat(flightManager.createFlight(1,
                "DUS", "BCN",
                LocalDateTime.of(2021, 10, 5, 9, 45),
                LocalDateTime.of(2021, 10, 6, 6, 0),
                "A-RRRR",
                new BigDecimal("200.00")).toString())
                .isEqualTo(f2.toString());
    }

    @Test
    public void addTest(){
//        TO DO
    }

    @Test
    public void getAllTest(){
        Mockito.when(fStorage.getAll()).thenReturn(flights);
        flights.add(f1);
        flights.add(f2);

        assertThat(flightManager.getFlights())
                .containsExactly(f1, f2);
    }

    @Test
    public void getLastIDTest(){
        Mockito.when(fStorage.getLastID()).thenReturn(5);

        assertThat(flightManager.getLastID())
                .isEqualTo(5);
    }
}
