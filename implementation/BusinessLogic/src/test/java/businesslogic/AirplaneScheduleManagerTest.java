package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneSchedule;
import businessentitiesapi.AirplaneScheduleManager;
import businessentitiesapi.Airport;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AirplaneScheduleManagerTest {
    @Mock
    DAOFactory daof;

    @Mock
    DAO<AirplaneSchedule, Serializable> dao;

    AirplaneScheduleManagerImpl apsm;


    Airplane ap1 = new Airplane(0, "Boeing 377","V-BBBB",367);
    Airplane ap2 = new Airplane(1, "Boeing 350","V-AAAA",250);
    Airplane ap3 = new Airplane(2, "Boeing 250","V-CCCC",20);

    LocalDateTime a = LocalDateTime.of(2021, 6, 21, 17,35);
    LocalDateTime b = LocalDateTime.of(2021, 6, 21, 22,25);
    LocalDateTime c = LocalDateTime.of(2021, 7, 5, 9,10);
    LocalDateTime d = LocalDateTime.of(2021, 7, 6, 4,50);
    LocalDateTime e = LocalDateTime.of(2021, 8, 17, 6,45);
    LocalDateTime f = LocalDateTime.of(2021, 8, 17, 12,0);

    AirplaneSchedule as1 = new AirplaneSchedule("V-AAAA", a, b);
    AirplaneSchedule as2 = new AirplaneSchedule("V-AAAA", c, d);
    AirplaneSchedule as3 = new AirplaneSchedule("V-AAAA", d, e);
    AirplaneSchedule as4 = new AirplaneSchedule("V-BBBB", b, c);
    AirplaneSchedule as5 = new AirplaneSchedule("V-CCCC", d, e);
    AirplaneSchedule as6 = new AirplaneSchedule("V-CCCC", c, e);

    LocalDateTimeRange r1 = LocalDateTimeRange.of(a, b);
    LocalDateTimeRange r2 = LocalDateTimeRange.of(c, d);
    LocalDateTimeRange r3 = LocalDateTimeRange.of(d, e);

    List<AirplaneSchedule> airplaneSchedule;

    String sql;

    @BeforeEach
    public void setupMock(){
        apsm = new AirplaneScheduleManagerImpl();

        airplaneSchedule = new ArrayList<>();

        daof = mock(DAOFactory.class);
        dao = mock(DAO.class);

        apsm.setDaoFactory(daof);

        Mockito.when(daof
                .createDao(AirplaneSchedule.class))
                .thenReturn(dao);

        apsm.setAirplaneScheduleStorageService(null, daof);

        sql = format(
                "select %1$s from %2$s where %3$s = ?",
                "airplaneCode, departureTime, arrivalTime",
                "airplaneSchedule",
                "airplaneCode");
    }

    @Test
    public void getAirplaneScheduleTest(){

        apsm.getAirplaneSchedule("V-AAAA");

        verify(dao).anyQuery(sql, "V-AAAA");
    }

    @Test
    public void airplaneScheduleRangeTest(){
        airplaneSchedule.add(as1);
        airplaneSchedule.add(as2);
        airplaneSchedule.add(as3);

        Mockito.when(dao.anyQuery(sql, "V-AAAA")).thenReturn(airplaneSchedule);

        assertThat(apsm.airplaneScheduleRange("V-AAAA")).containsExactly(r1, r2, r3);
    }

    @ParameterizedTest
    @CsvSource({
            "'V-AAAA', 2021, 6, 19, 17, 35, 2021, 6, 19, 22, 25, true",
            "'V-AAAA', 2021, 6, 21, 16, 35, 2021, 6, 21, 18, 25, false",
            "'V-AAAA', 2021, 6, 21, 18, 35, 2021, 6, 21, 23, 25, false",
            "'V-AAAA', 2021, 6, 21, 18, 35, 2021, 6, 21, 20, 25, false",
            "'V-AAAA', 2021, 8, 17, 12, 00, 2021, 9, 17, 12, 00, true"
    })
    public void airplaneCheckAvailability(String airplaneCode, int dYear, int dMonth, int dDay, int dHour, int dMinutes,
                                          int aYear, int aMonth, int aDay, int aHour, int aMinutes, boolean expected){

        LocalDateTime dTime = LocalDateTime.of(dYear, dMonth, dDay, dHour, dMinutes);
        LocalDateTime aTime = LocalDateTime.of(aYear, aMonth, aDay, aHour, aMinutes);

        airplaneSchedule.add(as1);
        airplaneSchedule.add(as2);
        airplaneSchedule.add(as3);

        Mockito.when(dao.anyQuery(sql, airplaneCode)).thenReturn(airplaneSchedule);


        if (!expected){
            ThrowableAssert.ThrowingCallable code = () -> {
                apsm.checkAvailability("V-AAAA", dTime, aTime);
            };

            assertThatThrownBy(code)
                    .isInstanceOf(Exception.class)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Airplane", "not", "available");
        } else {
            boolean result = apsm.checkAvailability("V-AAAA", dTime, aTime);

            assertThat(result).isTrue();
        }
    }
}
