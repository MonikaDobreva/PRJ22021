package businesslogic;

import businessentitiesapi.*;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.SeatStorageService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SeatManagerTest {

    @Mock
    DAOFactory daof;

    @Mock
    DAO<Seat, Serializable> dao;

    @Mock
    SeatStorageService sStorage;

    Seat s1 = new Seat(0, 4, "1A", 1);
    Seat s2 = new Seat(1, 4, "1B", 1);
    Seat s3 = new Seat(2, 3, "1C", 1);
    Seat s4 = new Seat(3, 3, "1D", 1);
    Seat s5 = new Seat(4, 2, "1F", 1);
    Seat s6 = new Seat(5, 1, "1G", 1);

    FlightSeat fs1 = new FlightSeat(0, 5, 1, true);

    Airplane ap = new Airplane(1, "Boeing 350","V-AAAA",250);

    List<Seat> seats;
    SeatManagerImpl seatManager;

    @BeforeEach
    public void setupMock(){
        seatManager = new SeatManagerImpl();

        seats = new ArrayList<>();

        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);

        seatManager.setDaoFactory(daof);

        Mockito.when(daof
                .createDao(Seat.class))
                .thenReturn(dao);
    }

    @Test
    public void createSeatTest(){
        assertThat(seatManager.createSeat(4, "1A", 1).toString())
                .isEqualTo(s1.toString());
    }

    @Test
    public void getAllTest(){
        seatManager.getSeats();

        verify(daof).createDao(Seat.class);
        verify(dao).getAll();
    }

    @Test
    public void addTest(){
        seatManager.add(s1);

        verify(daof).createDao(Seat.class);
        verify(dao).save(s1);
    }

    @Test
    public void getSeatsIdOfAirplaneTest(){
//        seats.add(s1);
//        seats.add(s2);
//        seats.add(s3);
//        seats.add(s4);
//        seats.add(s5);
//        seats.add(s6);
//        Mockito.when(sStorage.getSeatsOfAirplane(ap)).thenReturn(seats);
//
//        assertThat(seatManager.getSeatIdsOfAirplane(ap))
//                .contains(0, 1, 2, 3, 4, 5);
        String query = "select * from seatsView where seatsView.airplaneID = ? ";
        seatManager.getSeatIdsOfAirplane(ap);

        verify(daof).createDao(Seat.class);
        verify(dao).anyQuery(query, ap.getAirplaneID());
    }

    @Test
    public void getSeatForFlightSeat(){
        seatManager.getSeatForFlightSeat(fs1);

        verify(daof).createDao(Seat.class);
        verify(dao).get(fs1.getSeatId());
    }
}
