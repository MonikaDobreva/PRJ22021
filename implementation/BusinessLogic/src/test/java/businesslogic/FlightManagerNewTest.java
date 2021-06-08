package businesslogic;

import businessentitiesapi.Flight;
import genericdao.dao.DAOFactory;
import genericdao.dao.DAO;
import java.io.Serializable;
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
    DAOFactory daoF;

    @Mock
    DAO<Flight, Serializable> dao;

    FlightManagerImpl flm;
    Flight f;

    @BeforeEach
    public void setupTest() {
        flm = new FlightManagerImpl();
        daoF = mock(DAOFactory.class);
        dao = mock(DAO.class);
        flm.setDaoFactory(daoF);
        Mockito.when(daoF
                .createDao(Flight.class))
                .thenReturn(dao);

        f = new Flight(5,
                "DUS", "YVY",
                LocalDateTime.of(2021, 12, 3, 10, 30),
                LocalDateTime.of(2021, 12, 3, 15, 30),
                "V-BBBB",
                new BigDecimal("100.50"));
    }

//    @Disabled
    @Test
    public void updateTest() {
        boolean update = flm.update(f);
        verify(daoF).createDao(Flight.class);
        verify(dao).update(f);
        assertThat(update).isTrue();

    }

//    @Disabled
    @Test
    public void deleteTest() {
        boolean delete = flm.delete(f);
        verify(daoF).createDao(Flight.class);
        verify(dao).deleteEntity(f);
        assertThat(delete).isTrue();
    }
}
