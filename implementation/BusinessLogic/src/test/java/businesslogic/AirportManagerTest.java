package businesslogic;

import businessentitiesapi.Airport;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AirportManagerTest {

    @Mock
    DAOFactory daof;

    @Mock
    DAO<Airport, Serializable> dao;

    AirportManagerImpl apm;

    Airport ap1 = new Airport(0, "AMS", "Amsterdam", "Netherlands", "Amsterdam");
    Airport ap2 = new Airport(0, "BCN", "Barcelona", "Spain", "Barcelona");
    Airport ap3 = new Airport(0, "DUS", "Düsseldorf", "Germany", "Düsseldorf");

    List<Airport> airports;

    @BeforeEach
    public void setupMock(){
        apm = new AirportManagerImpl();

        airports = new ArrayList<>();

        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);

        apm.setDaoFactory(daof);

        Mockito.when(daof
                .createDao(Airport.class))
                .thenReturn(dao);

        apm.setAirportStorageService(null, daof);
    }

    @Test
    public void createAirportTest(){
        assertThat(apm.createAirport(0, "AMS", "Amsterdam", "Netherlands", "Amsterdam").toString())
                .isEqualTo(ap1.toString());
    }

    @Test
    public void getAirportsTest(){
        apm.getAirports();

        verify(dao).getAll();
    }

    @Test
    public void getAirportsWithoutOriginTest(){
        airports.add(ap1);
        airports.add(ap2);
        airports.add(ap3);

        Mockito.when(dao.getAll()).thenReturn(airports);

        assertThat(apm.getAirportsWithoutOrigin("AMS")).containsExactly(ap2, ap3);

    }

}
