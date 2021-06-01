package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import persistence.FlightRouteStorageService;
import persistence.FlightStorageService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FlightRouteManagerTest {

    @Mock
    FlightRouteStorageService frStorage;

    FlightRoute fr1 = new FlightRoute(1, "AMS", "BCN");
    FlightRoute fr2 = new FlightRoute(2, "DUS", "ATL");
    FlightRoute fr3 = new FlightRoute(3, "BER", "DXB");

    List<FlightRoute> flightRoutes;
    FlightRouteManagerImpl flightRouteManager = new FlightRouteManagerImpl();

    @BeforeEach
    public void setupMock(){
        flightRoutes = new ArrayList<>();
        frStorage = mock(FlightRouteStorageService.class);
        flightRouteManager.setFlightRouteStorageService(frStorage);
    }

    @Test
    public void createFlightRouteTest(){
        assertThat(flightRouteManager.createFlightRoute(2, "DUS", "ATL").toString())
                .isEqualTo(fr2.toString());
    }

    @Test
    public void getAllTest(){
        Mockito.when(frStorage.getAll()).thenReturn(flightRoutes);
        flightRoutes.add(fr1);
        flightRoutes.add(fr3);

        assertThat(flightRouteManager.getFlightRoutes())
                .containsExactly(fr1, fr3);
    }
}
