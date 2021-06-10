package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.Flight;
import businessentitiesapi.exceptions.FlightStorageException;
import genericdao.dao.DAOFactory;
import genericdao.dao.DAO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import persistence.AirplaneStorageService;
import persistence.FlightStorageService;
import persistence.FlightStorageServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This tests that in the flight manager the dao is called with the belonging
 * method. The dao is mocked to catch calls going there. The dao is fully
 * tested.
 *
 * @author Rachel
 */
public class FlightManagerTest {

    @Mock
    DAOFactory daoF;

    @Mock
    DAO<Flight, Serializable> dao;

    FlightManagerImpl flm;

    List<Flight> flights;

    Flight f1 = new Flight(0,
            "DUS", "YVY",
            LocalDateTime.of(2021, 12, 3, 10, 30),
            LocalDateTime.of(2021, 12, 3, 15, 30),
            "V-BBBB",
            new BigDecimal("100.50"));

    Flight f2 = new Flight(0,
            "DUS", "BCN",
            LocalDateTime.of(2021, 10, 5, 9, 45),
            LocalDateTime.of(2021, 10, 6, 6, 0),
            "A-RRRR",
            new BigDecimal("200.00"));

    Flight f3 = new Flight(0,
            "DUS", "AMS",
            LocalDateTime.of(2021, 8, 15, 9, 45),
            LocalDateTime.of(2021, 8, 15, 12, 0),
            "E-EEEE",
            new BigDecimal("300.00"));

    @BeforeEach
    public void setupTest() {
        flm = new FlightManagerImpl();

        daoF = mock(DAOFactory.class);
        dao = mock(DAO.class);

        flm.setDaoFactory(daoF);

        Mockito.when(daoF
                .createDao(Flight.class))
                .thenReturn(dao);

        flights = new ArrayList<>();
    }

    @Test
    public void createFlightTest() {
        assertThat(flm.createFlight(0,
                "DUS", "BCN",
                LocalDateTime.of(2021, 10, 5, 9, 45),
                LocalDateTime.of(2021, 10, 6, 6, 0),
                "A-RRRR",
                new BigDecimal("200.00")).toString())
                .isEqualTo(f2.toString());
    }

    @Disabled
    @Test
    public void addTest() throws FlightStorageException {
       Mockito.when(dao.save(f2)).thenReturn(Optional.of(f2));

        flm.add(f2);
        verify(daoF).createDao(Flight.class);
        verify(dao).save(f2);
    }

    @Test
    public void getAllTest() {
        flm.getFlights();

        verify(daoF).createDao(Flight.class);
        verify(dao).getAll();
    }

    //    @Disabled
    @Test
    public void updateTest() {
        assertThat(flm.update(f1)).isTrue();
        verify(daoF).createDao(Flight.class);
        verify(dao).update(f1);
    }

    //    @Disabled
    @Test
    public void updateBrokenTest() {
        Mockito.when(dao.update(f1)).thenThrow(RuntimeException.class);
        assertThat(flm.update(f1)).isFalse();
        verify(daoF).createDao(Flight.class);
        verify(dao).update(f1);
    }

    //    @Disabled
    @Test
    public void deleteTest() {
        assertThat(flm.delete(f1)).isTrue();
        verify(daoF).createDao(Flight.class);
        verify(dao).deleteEntity(f1);
    }
    
     //    @Disabled
    @Test
    public void deleteBrokenTest() {
        Mockito.doThrow(new RuntimeException()).when(dao).deleteEntity(f1);//because void type it is done like this
        assertThat(flm.delete(f1)).isFalse();
        verify(daoF).createDao(Flight.class);
        verify(dao).deleteEntity(f1);
    }

    @Test
    public void getLastIDTest() {
        flm.getLastID();

        verify(daoF).createDao(Flight.class);
        verify(dao).lastId();
    }

    @Test
    public void getFlightsByRouteIdTest() {
        flm.getFlightsByRouteId(1);

        verify(daoF).createDao(Flight.class);
        verify(dao).anyQuery(
                "select f.* "
                + "from flight_routes fr "
                + "join flights f on fr.id = f.flight_route_id "
                + "where fr.id = (?);", 1);
    }

}

//package businesslogic;
//
//import businessentitiesapi.Airplane;
//import businessentitiesapi.Flight;
//import businessentitiesapi.FlightManager;
//import businessentitiesapi.Manager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import persistence.AirplaneStorageService;
//import persistence.FlightStorageService;
//import persistence.FlightStorageServiceImpl;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
// */
//
//public class FlightManagerTest {
//
//    @Mock
//    FlightStorageService fStorage;
//
//    Flight f1 = new Flight(5,
//            "DUS", "YVY",
//            LocalDateTime.of(2021, 12, 3, 10, 30),
//            LocalDateTime.of(2021, 12, 3, 15, 30),
//            "V-BBBB",
//            new BigDecimal("100.50"));
//
//    Flight f2 = new Flight(1,
//            "DUS", "BCN",
//            LocalDateTime.of(2021, 10, 5, 9, 45),
//            LocalDateTime.of(2021, 10, 6, 6, 0),
//            "A-RRRR",
//            new BigDecimal("200.00"));
//
//    List<Flight> flights;
//    FlightManagerImpl flightManager = new FlightManagerImpl();
//
//    @BeforeEach
//    public void setupMock(){
//        flights = new ArrayList<>();
//        fStorage = mock(FlightStorageService.class);
//        flightManager.setFlightStorageService(fStorage);
//    }
//
//    @Test
//    public void createFlightTest(){
//        assertThat(flightManager.createFlight(1,
//                "DUS", "BCN",
//                LocalDateTime.of(2021, 10, 5, 9, 45),
//                LocalDateTime.of(2021, 10, 6, 6, 0),
//                "A-RRRR",
//                new BigDecimal("200.00")).toString())
//                .isEqualTo(f2.toString());
//    }
//
//    @Test
//    public void addTest(){
////        TO DO
//    }
//
//    @Test
//    public void getAllTest(){
//        Mockito.when(fStorage.getAll()).thenReturn(flights);
//        flights.add(f1);
//        flights.add(f2);
//
//        assertThat(flightManager.getFlights())
//                .containsExactly(f1, f2);
//    }
//
//    @Test
//    public void getLastIDTest(){
//        Mockito.when(fStorage.getLastID()).thenReturn(5);
//
//        assertThat(flightManager.getLastID())
//                .isEqualTo(5);
//    }
//}
