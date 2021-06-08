package businesslogic;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Flight;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;
import org.assertj.core.api.ThrowableAssert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.AirplaneStorageService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rachel
 */
//@ExtendWith( MockitoExtension.class )
public class AirplaneManagerTest {
    @Mock
    AirplaneStorageService apStorage;

    @Mock
    DAOFactory daof;

    @Mock
    DAO<Airplane, Serializable> dao;

    AirplaneManagerImpl apm;

    Airplane ap1 = new Airplane(0, "Boeing 377","V-BBBB",367);
    Airplane ap2 = new Airplane(1, "Boeing 350","V-AAAA",250);
    Airplane ap3 = new Airplane(2, "Boeing 250","V-CCCC",20);

    LocalDateTime a = LocalDateTime.of(2021, 6, 21, 17,35);
    LocalDateTime b = LocalDateTime.of(2021, 6, 21, 22,25);
    LocalDateTime c = LocalDateTime.of(2021, 7, 5, 9,10);
    LocalDateTime d = LocalDateTime.of(2021, 7, 6, 4,50);
    LocalDateTime e = LocalDateTime.of(2021, 8, 17, 6,45);
    LocalDateTime f = LocalDateTime.of(2021, 8, 17, 12,0);

    LocalDateTimeRange r1 = LocalDateTimeRange.of(a, b);
    LocalDateTimeRange r2 = LocalDateTimeRange.of(c, d);
    LocalDateTimeRange r3 = LocalDateTimeRange.of(e, f);

    List<Airplane> airplanes;
    List<LocalDateTimeRange> ap1Schedule;
    List<LocalDateTimeRange> ap2Schedule;
    AirplaneManagerImpl airplaneManager;

    @BeforeEach
    public void setupMock(){
        airplaneManager = new AirplaneManagerImpl();

        airplanes = new ArrayList<>();

        ap1Schedule = new ArrayList<LocalDateTimeRange>(){
            {add(r1); add(r3);}
        };
        ap2Schedule = new ArrayList<LocalDateTimeRange>(){
            {add(r2); add(r3);}
        };

        apm = new AirplaneManagerImpl();

        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);

        apm.setDaoFactory(daof);

        Mockito.when(daof
                .createDao(Airplane.class))
                .thenReturn(dao);

        apStorage = mock(AirplaneStorageService.class);
        airplaneManager.setAirplaneStorageService(apStorage, daof);
    }

    @Test
    public void createAirplaneTest(){
        assertThat(airplaneManager.createAirplane(0, "Boeing 377", "V-BBBB", 367).toString())
                .isEqualTo(ap1.toString());
    }

    
    @Test
    public void getAllTest(){
        apm.getAirplanes();

        verify(daof).createDao(Airplane.class);
        verify(dao).getAll();
    }

    @Test
    public void getAirplaneTest(){
        airplanes.add(ap1);
        airplanes.add(ap2);
        airplanes.add(ap3);

        Mockito.when(dao.getAll()).thenReturn(airplanes);

        Airplane result = apm.getAirplane("V-AAAA");

        verify(daof).createDao(Airplane.class);
        verify(dao).getAll();

        assertThat(result).isEqualTo(ap2);
    }
}
