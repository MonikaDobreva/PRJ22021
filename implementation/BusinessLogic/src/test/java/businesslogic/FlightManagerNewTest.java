package businesslogic;

import businessentitiesapi.Flight;
import genericdao.pgdao.PGDAOFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import persistence.AirplaneStorageService;
import persistence.FlightStorageService;
import persistence.FlightStorageServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This tests that in the flight manager the dao is called with the belonging
 * method. The dao is mocked to catch calls going there. The dao is fully
 * tested.
 *
 * @author Rachel
 */
public class FlightManagerNewTest {

    @Mock
    PGDAOFactory daoM;

    FlightManagerImpl flm;
    Flight f;

    @BeforeEach
    public void setupTest() {
        flm = new FlightManagerImpl();
        daoM = mock(PGDAOFactory.class);
        flm.setDaoFactory(daoM);

        f = new Flight(5,
                "DUS", "YVY",
                LocalDateTime.of(2021, 12, 3, 10, 30),
                LocalDateTime.of(2021, 12, 3, 15, 30),
                "V-BBBB",
                new BigDecimal("100.50"));
    }

    @Disabled
    @Test
    public void updateTest() {
//        Mockito.when(daoM.createDao(forClass)).thenReturn(daoM);
        flm.update(f);
        verify(daoM).createDao(Flight.class).update(f);

    }
    
    @Disabled
    @Test
    public void deleteTest() {
//        Mockito.when(daoM.createDao(forClass)).thenReturn(daoM);
        flm.delete(f);
        verify(daoM).createDao(Flight.class).deleteEntity(f);

    }
}
