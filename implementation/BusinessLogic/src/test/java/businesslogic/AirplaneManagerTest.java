package businesslogic;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rachel
 */
//@ExtendWith( MockitoExtension.class )
public class AirplaneManagerTest {

    AirplaneStorageService apStorage;

    Airplane ap1 = new Airplane(0, "Boeing 377","V-BBBB",367);
    Airplane ap2 = new Airplane(1, "Boeing 350","V-AAAA",250);

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
    AirplaneManagerImpl airplaneManager = new AirplaneManagerImpl();

    @BeforeEach
    public void setupMock(){
        airplanes = new ArrayList<>();
        ap1Schedule = new ArrayList<LocalDateTimeRange>(){
            {add(r1); add(r3);}
        };
        ap2Schedule = new ArrayList<LocalDateTimeRange>(){
            {add(r2); add(r3);}
        };
        apStorage = mock(AirplaneStorageService.class);
        airplaneManager.setAirplaneStorageService(apStorage);
    }

    @Test
    public void createAirplaneTest(){
        assertThat(airplaneManager.createAirplane(0, "Boeing 377", "V-BBBB", 367).toString())
                .isEqualTo(ap1.toString());
    }


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

    @Test
    public void airplaneScheduleTest(){
        Mockito.when(apStorage.getSchedule(ap1)).thenReturn(ap1Schedule);
        Mockito.when(apStorage.getSchedule(ap2)).thenReturn(ap2Schedule);

        assertThat(airplaneManager.airplaneSchedule(ap1))
                .containsExactly(r1, r3);

        assertThat(airplaneManager.airplaneSchedule(ap2))
                .containsExactly(r2, r3);
    }

    @ParameterizedTest
    @CsvSource({
            "2021, 6, 19, 17, 35, 2021, 6, 19, 22, 25, true",
            "2021, 6, 21, 16, 35, 2021, 6, 21, 18, 25, false",
            "2021, 6, 21, 18, 35, 2021, 6, 21, 23, 25, false",
            "2021, 6, 21, 18, 35, 2021, 6, 21, 20, 25, false",
            "2021, 7, 23, 17, 35, 2021, 7, 23, 22, 25, true",
            "2021, 8, 17, 5, 35, 2021, 8, 17, 8, 25, false",
            "2021, 8, 17, 8, 35, 2021, 8, 17, 11, 25, false",
            "2021, 8, 17, 11, 35, 2021, 8, 17, 18, 25, false",
            "2021, 8, 17, 17, 35, 2021, 8, 17, 22, 25, true",
    })
    public void airplaneCheckAvailability(int dYear, int dMonth, int dDay, int dHour, int dMinutes,
                                          int aYear, int aMonth, int aDay, int aHour, int aMinutes, boolean expected){
        Mockito.when(airplaneManager.airplaneSchedule(ap1)).thenReturn(ap1Schedule);

        if (!expected){
            ThrowingCallable code = () -> {
                airplaneManager.checkAvailability(
                        ap1,
                        LocalDateTime.of(dYear, dMonth, dDay, dHour, dMinutes),
                        LocalDateTime.of(aYear, aMonth, aDay, aHour, aMinutes));
            };

            assertThatThrownBy(code)
                    .isInstanceOf(Exception.class)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Airplane", "not", "available");
        } else {
            boolean result = airplaneManager.checkAvailability(
                    ap1,
                    LocalDateTime.of(dYear, dMonth, dDay, dHour, dMinutes),
                    LocalDateTime.of(aYear, aMonth, aDay, aHour, aMinutes));

            assertThat(result).isTrue();
        }
    }
}
