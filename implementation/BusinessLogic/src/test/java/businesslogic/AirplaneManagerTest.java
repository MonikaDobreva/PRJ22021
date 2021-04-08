package businesslogic;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.AirplaneStorageService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rachel
 */
@ExtendWith( MockitoExtension.class )
public class AirplaneManagerTest {

//    @Mock
    AirplaneStorageService apStorage;

    Airplane ap1 = new AirplaneImpl("Boeing 377","V-BBBB",367);
    Airplane ap2 = new AirplaneImpl("Boeing 350","V-AAAA",250);

    List<Airplane> airplanes;
    AirplaneManagerImpl airplaneManager = new AirplaneManagerImpl();

    @BeforeEach
    public void setupMock(){
        airplanes = new ArrayList<>();
        apStorage = mock(AirplaneStorageService.class);
        airplaneManager.setAirplaneStorageService(apStorage);
    }

    @Test
    public void createAirplaneTest(){
        assertThat(airplaneManager.createAirplane("Boeing 377", "V-BBBB", 367).toString())
                .isEqualTo(ap1.toString());
    }

//    @Test
//    public void addTest(){
////        TO DO
//    }

//    @Test
//    public void addTest(){
////        Mockito.doAnswer(a -> {
////            airplanes.add(ap1);
////            assertThat(airplanes)
////                    .containsExactly(ap1);
////            return null;
////        }).when(apStorage).add(ap1);
//        Mockito.doReturn(airplanes).when(apStorage).add(ap1);
//        Mockito.doReturn(airplanes).when(apStorage).add(ap2);
//
//        assertThat(airplanes)
//                .containsExactly(ap1, ap2);
//
//    }

    @Test
    public void getTest(){
        Mockito.when(apStorage.getAll()).thenReturn(airplanes);
        airplanes.add(ap1);
        airplanes.add(ap2);

        assertThat(airplaneManager.getAirplanes())
                .containsExactly(ap1, ap2);
    }
}
