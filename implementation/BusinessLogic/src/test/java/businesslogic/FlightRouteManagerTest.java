package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.FlightRouteStorageService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FlightRouteManagerTest {

    @Mock
    DAOFactory daof;

    @Mock
    DAO<FlightRoute, Serializable> dao;

    FlightRoute fr1 = new FlightRoute(0, "AMS", "BCN");
    FlightRoute fr2 = new FlightRoute(0, "DUS", "ATL");
    FlightRoute fr3 = new FlightRoute(0, "BER", "DXB");

    List<FlightRoute> flightRoutes;
    FlightRouteManagerImpl flightRouteManager;

    @BeforeEach
    public void setupMock(){
        flightRouteManager = new FlightRouteManagerImpl();

        flightRoutes = new ArrayList<>();

        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);

        flightRouteManager.setDaoFactory(daof);

        Mockito.when(daof
                .createDao(FlightRoute.class))
                .thenReturn(dao);
    }

    @Test
    public void createFlightRouteTest(){
        assertThat(flightRouteManager.createFlightRoute(0, "DUS", "ATL").toString())
                .isEqualTo(fr2.toString());
    }

    @Test
    public void getAllTest(){
        flightRouteManager.getFlightRoutes();

        verify(daof).createDao(FlightRoute.class);
        verify(dao).getAll();
    }

    @Test
    public void addTest(){
        flightRouteManager.add(fr2);
        verify(daof).createDao(FlightRoute.class);
        verify(dao).save(fr2);
    }

    @ParameterizedTest
    @CsvSource({
            "'DUS', 'ATL', true",
            "'EIN', 'BRU', false",
    })
    public void checkExistenceTest(String origin, String destination, boolean exists){
//        flightRoutes.add(fr1);
//        flightRoutes.add(fr2);
//        flightRoutes.add(fr3);
//
//        FlightRoute fr = new FlightRoute(0, origin, destination );
//
//        Mockito.when(dao.getAll()).thenReturn(flightRoutes);
//        flightRouteManager.checkExistence(origin, destination);
//
//        if (exists){
//            verify(dao, times(0)).save(fr);
//        } else {
//            verify(dao).save(fr);
//        }
    }

}
