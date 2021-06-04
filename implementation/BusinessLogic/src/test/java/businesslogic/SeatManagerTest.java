package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.SeatStorageService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SeatManagerTest {

    @Mock
    SeatStorageService sStorage;

    Seat s1 = new Seat(0, 4, "1A", 1);
    Seat s2 = new Seat(1, 4, "1B", 1);
    Seat s3 = new Seat(2, 3, "1C", 1);
    Seat s4 = new Seat(3, 3, "1D", 1);
    Seat s5 = new Seat(4, 2, "1F", 1);
    Seat s6 = new Seat(5, 1, "1G", 1);

    Airplane ap = new Airplane(1, "Boeing 350","V-AAAA",250);

    List<Seat> seats;
    SeatManagerImpl seatManager = new SeatManagerImpl();

    @BeforeEach
    public void setupMock(){
        seats = new ArrayList<>();
        sStorage = mock(SeatStorageService.class);
        seatManager.setSeatStorageService(sStorage);
    }

    @Test
    public void createSeatTest(){
        assertThat(seatManager.createSeat(4, "1A", 1).toString())
                .isEqualTo(s1.toString());
    }

    @Test
    public void getAllTest(){
        Mockito.when(sStorage.getAll()).thenReturn(seats);
        seats.add(s1);
        seats.add(s2);
        seats.add(s4);
        seats.add(s5);
        seats.add(s6);

        assertThat(seatManager.getSeats())
                .containsExactly(s1, s2, s4, s5, s6);
    }

    @Test
    public void addTest(){
        Mockito.when(sStorage.add(s4)).thenReturn(s4);

        assertThat(seatManager.add(s4).toString())
                .isEqualTo(s4.toString());
    }

    @Test
    public void getSeatsIdOfAirplaneTest(){
        seats.add(s1);
        seats.add(s2);
        seats.add(s3);
        seats.add(s4);
        seats.add(s5);
        seats.add(s6);
        Mockito.when(sStorage.getSeatsOfAirplane(ap)).thenReturn(seats);

        assertThat(seatManager.getSeatIdsOfAirplane(ap))
                .contains(0, 1, 2, 3, 4, 5);
    }
}
