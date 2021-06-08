package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightSeat;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import java.io.Serializable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.FlightSeatStorageService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FlightSeatManagerTest {
    @Mock
    DAOFactory daof;

    @Mock
    DAO<FlightSeat, Serializable> dao;

    @Mock
    FlightSeatStorageService fsStorage;

    FlightSeat fs1 = new FlightSeat(0, 10, 1, true);
    FlightSeat fs2 = new FlightSeat(0, 11, 1, true);
    FlightSeat fs3 = new FlightSeat(0, 12, 1, true);
    FlightSeat fs4 = new FlightSeat(0, 13, 1, true);
    FlightSeat fs5 = new FlightSeat(0, 14, 1, true);
    FlightSeat fs6 = new FlightSeat(0, 15, 1, true);

    Flight f1 = new Flight(1,
            "DUS", "YVY",
            LocalDateTime.of(2021, 12, 3, 10, 30),
            LocalDateTime.of(2021, 12, 3, 15, 30),
            "V-BBBB",
            new BigDecimal("100.50"));

    List<FlightSeat> flightSeats;
    FlightSeatManagerImpl flightSeatManager;

    @BeforeEach
    public void setupMock(){
        flightSeatManager = new FlightSeatManagerImpl();

        flightSeats = new ArrayList<>();
        fsStorage = mock(FlightSeatStorageService.class);

        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);
        flightSeatManager.setDaoFactory(daof);
        Mockito.when(daof
                .createDao(FlightSeat.class))
                .thenReturn(dao);
    }

    @Test
    public void createFlightSeatTest(){
        assertThat(flightSeatManager.createFlightSeat(10, 1, true).toString())
                .isEqualTo(fs1.toString());
    }

    @Test
    public void getAllTest(){
        flightSeatManager.getFlightSeats();

        verify(daof).createDao(FlightSeat.class);
        verify(dao).getAll();
    }

    @Test
    public void addTest(){
        flightSeatManager.add(fs1);

        verify(daof).createDao(FlightSeat.class);
        verify(dao).save(fs1);
    }

    @Test
    public void getAvailableFlightSeatsTest(){
        String query = "SELECT *\n" +
                "FROM flightSeatsView\n" +
                "         JOIN seatsview s on flightseatsview.seatid = s.seatid\n" +
                "         JOIN seattypesview s2 on s.seattypeid = s2.seattypeid\n" +
                "WHERE flightId = ?\n" +
                "  AND available = true\n" +
                "  AND s2.name = ?;";

        flightSeatManager.getAvailableFlightSeats(f1, "4");

        verify(daof).createDao(FlightSeat.class);
        verify(dao).anyQuery(query, 1, "4");
    }

}
