package businesslogic;

import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightSeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.FlightRouteStorageService;
import persistence.FlightSeatStorageService;
import persistence.FlightStorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FlightSeatManagerTest {

    @Mock
    FlightSeatStorageService fsStorage;

    FlightSeat fs1 = new FlightSeat(0, 10, 1, true);
    FlightSeat fs2 = new FlightSeat(2, 11, 1, true);
    FlightSeat fs3 = new FlightSeat(3, 12, 1, true);
    FlightSeat fs4 = new FlightSeat(4, 13, 1, true);
    FlightSeat fs5 = new FlightSeat(5, 14, 1, true);
    FlightSeat fs6 = new FlightSeat(6, 15, 1, true);

    List<FlightSeat> flightSeats;
    FlightSeatManagerImpl flightSeatManager = new FlightSeatManagerImpl();

    @BeforeEach
    public void setupMock(){
        flightSeats = new ArrayList<>();
        fsStorage = mock(FlightSeatStorageService.class);
        flightSeatManager.setFlightSeatStorageService(fsStorage);
    }

    @Test
    public void createFlightSeatTest(){
        assertThat(flightSeatManager.createFlightSeat(10, 1, true).toString())
                .isEqualTo(fs1.toString());
    }

    @Test
    public void getAllTest(){
        Mockito.when(fsStorage.getAll()).thenReturn(flightSeats);
        flightSeats.add(fs1);
        flightSeats.add(fs2);
        flightSeats.add(fs4);
        flightSeats.add(fs5);
        flightSeats.add(fs6);

        assertThat(flightSeatManager.getFlightSeats())
                .containsExactly(fs1, fs2, fs4, fs5, fs6);
    }

    @Test
    public void addTest(){
        Mockito.when(fsStorage.add(fs4)).thenReturn(fs4);

        assertThat(flightSeatManager.add(fs4).toString())
                .isEqualTo(fs4.toString());
    }

    @Test
    public void addAllTest(){
//        flightSeats.add(fs1);
//        flightSeats.add(fs2);
//        flightSeats.add(fs3);
//        flightSeats.add(fs4);
//        flightSeats.add(fs5);
//        flightSeats.add(fs6);
//        Mockito.when(fsStorage.addAll(flightSeats)).thenReturn(flightSeats);
//
//        List<Integer> seatsId = new ArrayList<>();
//        seatsId.add(10);
//        seatsId.add(11);
//        seatsId.add(12);
//        seatsId.add(13);
//        seatsId.add(14);
//        seatsId.add(15);
//
//        assertThat(flightSeatManager.addAll(seatsId, 1))
//                .contains(fs1, fs2, fs3, fs4, fs5, fs6);
    }

}
