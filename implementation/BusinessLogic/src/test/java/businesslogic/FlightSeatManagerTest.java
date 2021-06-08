//package businesslogic;
//
//import businessentitiesapi.FlightRoute;
//import businessentitiesapi.FlightSeat;
//import genericdao.dao.DAO;
//import genericdao.dao.DAOFactory;
//import java.io.Serializable;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import persistence.FlightSeatStorageService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//public class FlightSeatManagerTest {
//    @Mock
//    DAOFactory daof;
//
//    @Mock
//    DAO<FlightSeat, Serializable> dao;
//
//    @Mock
//    FlightSeatStorageService fsStorage;
//
//    FlightSeat fs1 = new FlightSeat(0, 10, 1, true);
//    FlightSeat fs2 = new FlightSeat(0, 11, 1, true);
//    FlightSeat fs3 = new FlightSeat(0, 12, 1, true);
//    FlightSeat fs4 = new FlightSeat(0, 13, 1, true);
//    FlightSeat fs5 = new FlightSeat(0, 14, 1, true);
//    FlightSeat fs6 = new FlightSeat(0, 15, 1, true);
//
//    List<FlightSeat> flightSeats;
//    FlightSeatManagerImpl flightSeatManager;
//
//    @BeforeEach
//    public void setupMock(){
//        flightSeatManager = new FlightSeatManagerImpl();
//
//        flightSeats = new ArrayList<>();
//        fsStorage = mock(FlightSeatStorageService.class);
//
//        daof = mock(DAOFactory.class);
//        dao = mock(DAO.class);
//        flightSeatManager.setDaoFactory(daof);
//        Mockito.when(daof
//                .createDao(FlightSeat.class))
//                .thenReturn(dao);
//    }
//
//    @Test
//    public void createFlightSeatTest(){
//        assertThat(flightSeatManager.createFlightSeat(10, 0, true).toString())
//                .isEqualTo(fs1.toString());
//    }
//
//    @Test
//    public void getAllTest(){
////        Mockito.when(fsStorage.getAll()).thenReturn(flightSeats);
////        flightSeats.add(fs1);
////        flightSeats.add(fs2);
////        flightSeats.add(fs4);
////        flightSeats.add(fs5);
////        flightSeats.add(fs6);
////
////        assertThat(flightSeatManager.getFlightSeats())
////                .containsExactly(fs1, fs2, fs4, fs5, fs6);
//
//        flightSeatManager.getFlightSeats();
//
//        verify(daof).createDao(FlightSeat.class);
//        verify(dao).getAll();
//    }
//
//    @Test
//    public void addTest(){
////        Mockito.when(fsStorage.add(fs4)).thenReturn(fs4);
////
////        assertThat(flightSeatManager.add(fs4).toString())
////                .isEqualTo(fs4.toString());
//        flightSeatManager.add(fs1);
//
//        verify(daof).createDao(FlightSeat.class);
//        verify(dao).save(fs1);
//    }
//
//    @Test
//    public void addAllTest(){
//
//        flightSeats.add(fs1);
//        flightSeats.add(fs2);
//        flightSeats.add(fs3);
//        flightSeats.add(fs4);
//        flightSeats.add(fs5);
//        flightSeats.add(fs6);
//
////        Mockito.when(fsStorage.addAll(flightSeats)).thenReturn(flightSeats);
////
//        List<Integer> seatsId = new ArrayList<>();
//        seatsId.add(10);
//        seatsId.add(11);
//        seatsId.add(12);
//        seatsId.add(13);
//        seatsId.add(14);
//        seatsId.add(15);
//
//        flightSeatManager.addAll(seatsId, 1);
//
//        verify(daof).createDao(FlightSeat.class);
//
//        verify(dao).saveAll(flightSeats);
////
////        assertThat(flightSeatManager.addAll(seatsId, 1))
////                .contains(fs1, fs2, fs3, fs4, fs5, fs6);
//    }
//
//}
