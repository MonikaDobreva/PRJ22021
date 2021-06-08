package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.FlightRouteStorageService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FlightRouteManagerTest {

    @Mock
    DAOFactory daof;

    @Mock
    DAO<FlightRoute, Serializable> dao;

    @Mock
    FlightRouteStorageService frStorage;

    FlightRoute fr1 = new FlightRoute(1, "AMS", "BCN");
    FlightRoute fr2 = new FlightRoute(2, "DUS", "ATL");
    FlightRoute fr3 = new FlightRoute(3, "BER", "DXB");

    List<FlightRoute> flightRoutes;
    FlightRouteManagerImpl flightRouteManager;

    @BeforeEach
    public void setupMock(){
        flightRouteManager = new FlightRouteManagerImpl();;
        flightRoutes = new ArrayList<>();
//        frStorage = mock(FlightRouteStorageService.class);
        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);
        flightRouteManager.setDaoFactory(daof);
        Mockito.when(daof
                .createDao(FlightRoute.class))
                .thenReturn(dao);
    }

    @Test
    public void createFlightRouteTest(){
        assertThat(flightRouteManager.createFlightRoute(2, "DUS", "ATL").toString())
                .isEqualTo(fr2.toString());
    }

    @Test
    public void getAllTest(){

        flightRouteManager.getFlightRoutes();

        verify(daof).createDao(FlightRoute.class);
        verify(dao).getAll();

//        Mockito.when(dao.getAll()).thenReturn(flightRoutes);
//        flightRoutes.add(fr1);
//        flightRoutes.add(fr3);
//
//        assertThat(flightRouteManager.getFlightRoutes())
//                .containsExactly(fr1, fr3);
    }

}
