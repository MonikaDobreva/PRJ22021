package businesslogic;

import businessentitiesapi.FlightRoute;
import businessentitiesapi.exceptions.FlightStorageException;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
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
    public void addTest() throws FlightStorageException {
        Mockito.when(dao.save(fr2)).thenReturn(Optional.of(fr2));
        assertThat(flightRouteManager.add(fr2)).isEqualTo(fr2);
        verify(daof).createDao(FlightRoute.class);
        verify(dao).save(fr2);

    }

    @Test
    public void checkExistenceOKTest(){
        flightRoutes.add(fr1);
        flightRoutes.add(fr2);
        flightRoutes.add(fr3);

        FlightRoute fr = new FlightRoute(0, "DUS", "ATL" );

        Mockito.when(dao.getAll()).thenReturn(flightRoutes);
        flightRouteManager.checkExistence("DUS", "ATL");

        verify(dao).getAll();
        verify(dao, times(0)).save(fr);
    }

    @Test
    public void checkExistenceNotExistsTest(){
        flightRoutes.add(fr1);
        flightRoutes.add(fr2);
        flightRoutes.add(fr3);

        FlightRoute fr = flightRouteManager.createFlightRoute(0, "EIN", "BRU");

        Mockito.when(dao.getAll()).thenReturn(flightRoutes);
        Mockito.when(dao.save(fr)).thenReturn(Optional.of(fr));

        flightRouteManager.checkExistence("EIN", "BRU");

        verify(dao).getAll();
    }

    @Test
    public void setFlightRouteStorageServiceTest(){
        ThrowableAssert.ThrowingCallable code = () -> {
            flightRouteManager.setFlightRouteStorageService(null, daof);
        };
        assertThatCode(code).doesNotThrowAnyException();
    }

}
