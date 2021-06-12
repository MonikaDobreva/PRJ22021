package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.exceptions.FlightStorageException;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    public void addTest() throws FlightStorageException {
       Mockito.when(dao.save(f2)).thenReturn(Optional.of(f2));

        flm.add(f2);
        verify(daoF).createDao(Flight.class);
        verify(dao).save(f2);
    }

    @Test
    public void addBrokenTest() {
        Mockito.when(dao.save(f1)).thenThrow(RuntimeException.class);
        assertThat(flm.add(f1)).isNull();
        verify(daoF).createDao(Flight.class);
        verify(dao).save(f1);
    }

    @Test
    public void getAllTest() {
        flm.getFlights();

        verify(daoF).createDao(Flight.class);
        verify(dao).getAll();
    }

    @Test
    public void getAllBrokenTest() {
        Mockito.when(dao.getAll()).thenThrow(RuntimeException.class);
        assertThat(flm.getFlights()).isNull();
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
    public void getLastIDBrokenTest() {
        Mockito.doThrow(new RuntimeException()).when(dao).lastId();//because void type it is done like this

        assertThat(flm.getLastID()).isEqualTo(0);
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

    @Test
    public void getFlightsByRouteIDBrokenTest() {
        Mockito.doThrow(new RuntimeException()).when(dao).anyQuery(
                "select f.* "
                        + "from flight_routes fr "
                        + "join flights f on fr.id = f.flight_route_id "
                        + "where fr.id = (?);", 1);

        assertThat(flm.getFlightsByRouteId(1)).isNull();
        verify(daoF).createDao(Flight.class);
        verify(dao).anyQuery(
                "select f.* "
                        + "from flight_routes fr "
                        + "join flights f on fr.id = f.flight_route_id "
                        + "where fr.id = (?);", 1);
    }

    @Test
    public void tCalcEST(){
        LocalDateTime d1 = LocalDateTime.parse("2022-12-12T10:00:00");
        LocalDateTime d2 = LocalDateTime.parse("2022-12-12T13:00:00");
        assertThat(flm.calcEST(d1, d2)).isEqualTo(180L);
    }

}
