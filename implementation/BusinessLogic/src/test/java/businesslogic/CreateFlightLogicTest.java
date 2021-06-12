package businesslogic;

import businessentitiesapi.*;
import businesslogic.testHelpers.CreateLogicHelper;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CreateFlightLogicTest {

    @Mock
    FlightManager fm;
    @Mock
    FlightRouteManager frm;
    @Mock
    FlightSeatManager fsm;
    @Mock
    AirportManager am;
    @Mock
    AirplaneManager apm;
    @Mock
    AirplaneScheduleManager apsm;
    @Mock
    SeatManager sm;

    CreateFlightLogic cfl;

    Map<String, String> values;

    @BeforeEach
    public void setupMock(){
        fm = Mockito.mock(FlightManager.class);
        frm = Mockito.mock(FlightRouteManager.class);
        fsm = Mockito.mock(FlightSeatManager.class);
        am = Mockito.mock(AirportManager.class);
        apm = Mockito.mock(AirplaneManager.class);
        apsm = Mockito.mock(AirplaneScheduleManager.class);
        sm = Mockito.mock(SeatManager.class);

        cfl = new CreateFlightLogic(apm, am, fm, frm, sm, fsm, apsm);

        values = new HashMap<>();
    }

    /**
     * This test is only for testing that a zero is appended at the beginning of
     * a single digit from hours and min.
     *
     * @param hour
     * @param min
     * @param d date
     * @param expected dateTime
     */
    @ParameterizedTest
    @CsvSource({
            //hour, min date
            "23,20,2021-04-18,23:20 2021-04-18",
            "0,1,2021-04-20,00:01 2021-04-20",
            "12,3,2021-07-10,12:03 2021-07-10"
    })
    public void makeDateTimeTest(String hour, String min, String d, String expected) {
        LocalDateTime expectedDate = LocalDateTime.parse(
                expected,
                DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
        assertThat(cfl.makeDateTime(hour, min, d)).isEqualTo(expectedDate);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 10",
            "1, 01"
    })
    public void makeTimeValidOKTest(String in, String out){
        assertThat(cfl.makeTimeValid(in)).isEqualTo(out);
    }

    @ParameterizedTest
    @CsvSource({
            "'0', 'AMS', 'DUS', '10', '30', '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'"
    })
    public void dataValidationOKTest(String v0, String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8,
                          String v9, String v10){
        values = CreateLogicHelper.getValues(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);

        ThrowableAssert.ThrowingCallable code = () -> {
            cfl.dataValidation(values);
        };

        assertThatCode(code).doesNotThrowAnyException();

    }

    @ParameterizedTest
    @CsvSource({
            "'AA', 'AMS', 'DUS', '10', '30', '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
            "'1', 'AMS', 'DUS', , '30', '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
            "'1', 'AMS', 'DUS', '10', , '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
            "'1', 'AMS', 'DUS', '10', '30', , '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
            "'1', 'AMS', 'DUS', '10', '30', '2022-10-05', , '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
            "'1', 'AMS', 'DUS', '10', '30', '2022-10-05', '12', , '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
            "'1', 'AMS', 'DUS', '10', '30', '2022-10-05', '12', '45', , 'X-WXYZ (Mini 26-Seater)', '120.00",
            "'1', 'AMS', 'DUS', '10', '30', '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', 'AA'",
            "'1', 'AMS', 'DUS', '10', '30', '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120,00'",
            "'1', 'AMS', 'DUS', null, '30', '2022-10-05', '12', '45', '2022-10-05', 'X-WXYZ (Mini 26-Seater)', '120.00'",
    })
    public void dataValidationNotOKTest(String v0, String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8,
                                     String v9, String v10){
        values = CreateLogicHelper.getValues(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);

        ThrowableAssert.ThrowingCallable code = () -> {
            cfl.dataValidation(values);
        };

        assertThatThrownBy(code).isInstanceOf(Exception.class);

    }

    @Test
    public void obtainAirplaneTest(){
        values = CreateLogicHelper.getValues();
        cfl.setData(values);

        cfl.obtainAirplane();
        Mockito.verify(apm).getAirplane("X-WXYZ");
    }

    @Test
    public void obtainFlightsTest(){
        List<Flight> flights = CreateLogicHelper.getFlights();

        Mockito.when(fm.getFlights()).thenReturn(flights);
        assertThat(cfl.obtainFlights()).containsExactly(CreateLogicHelper.f1, CreateLogicHelper.f2, CreateLogicHelper.f3);
    }

    @Test
    public void listOriginAirportTest(){
        List<Airport> airports = CreateLogicHelper.getAirports();

        Mockito.when(am.getAirports()).thenReturn(airports);
        assertThat(cfl.listOriginAirport()).containsExactly("AMS", "BCN", "DUS");
        Mockito.verify(am).getAirports();
    }

    @Test
    public void listDestinationAirportTest(){
        List<Airport> airports = CreateLogicHelper.getAirports();
        airports.remove(CreateLogicHelper.ap2);

        Mockito.when(am.getAirportsWithoutOrigin("BCN")).thenReturn(airports);
        assertThat(cfl.listDestinationAirport("BCN")).containsExactly("AMS", "DUS");
        Mockito.verify(am).getAirportsWithoutOrigin("BCN");
    }

    @Test
    public void listAirplanesTest(){
        List<Airplane> airplanes = CreateLogicHelper.getAirplanes();

        Mockito.when(apm.getAirplanes()).thenReturn(airplanes);
        assertThat(cfl.listAirplanes()).containsExactly("V-BBBB (Boeing 377)", "V-AAAA (Boeing 350)" , "V-CCCC (Boeing 250)" );
        Mockito.verify(apm).getAirplanes();

    }

    @Test
    public void nextIdTest(){
        Mockito.when(fm.getLastID()).thenReturn(12);
        assertThat(cfl.getNextID()).isEqualTo("13");
        Mockito.verify(fm).getLastID();
    }

    @Test
    public void getAirportInfoTest(){
        Mockito.when(am.getAirport("BCN")).thenReturn(CreateLogicHelper.ap2);
        assertThat(cfl.getAirportInfo("BCN").toString()).isEqualTo(CreateLogicHelper.ap2.toString());
        Mockito.verify(am).getAirport("BCN");
    }

    @Test
    public void getAirplaneInfoTest(){
        Mockito.when(apm.getAirplane("V-AAAA")).thenReturn(CreateLogicHelper.a2);
        assertThat(cfl.getAirplaneInfo("V-AAAA").toString()).isEqualTo(CreateLogicHelper.a2.toString());
        Mockito.verify(apm).getAirplane("V-AAAA");
    }

    @Test
    public void storeFlightOKTest(){
        values = CreateLogicHelper.getValues();
        cfl.setData(values);

        LocalDateTime depTime = cfl.makeDateTime("10", "30", "2022-10-10");
        LocalDateTime arrTime = cfl.makeDateTime("12", "45", "2022-10-11");

        Mockito.when(apm.getAirplane("X-WXYZ")).thenReturn(CreateLogicHelper.a4);
        Mockito.when(apsm.checkAvailability("X-WXYZ", depTime, arrTime)).thenReturn(true);
        Mockito.when(fm.createFlight(0,
                "AMS", "DUS",
                LocalDateTime.of(2022, 10, 10, 10, 30),
                LocalDateTime.of(2022, 10, 11, 12, 45),
                "X-WXYZ",
                new BigDecimal("120.00"))).thenReturn(CreateLogicHelper.f);
        Mockito.when(fm.add(CreateLogicHelper.f)).thenReturn(CreateLogicHelper.f);
        Mockito.when(sm.getSeatIdsOfAirplane(CreateLogicHelper.a4)).thenReturn(CreateLogicHelper.getSeatsId());
        Mockito.when(fsm.createFlightSeat(0, 0, true)).thenReturn(CreateLogicHelper.fs1);
        Mockito.when(fsm.createFlightSeat(1, 0, true)).thenReturn(CreateLogicHelper.fs2);
        Mockito.when(fsm.createFlightSeat(2, 0, true)).thenReturn(CreateLogicHelper.fs3);
        Mockito.when(fsm.add(CreateLogicHelper.fs1)).thenReturn(CreateLogicHelper.fs1);
        Mockito.when(fsm.add(CreateLogicHelper.fs2)).thenReturn(CreateLogicHelper.fs2);
        Mockito.when(fsm.add(CreateLogicHelper.fs3)).thenReturn(CreateLogicHelper.fs3);

        ThrowableAssert.ThrowingCallable code = () -> {
            cfl.storeFlight();
        };

        assertThatCode(code).doesNotThrowAnyException();

        Mockito.verify(apm).getAirplane(Mockito.anyString());
        Mockito.verify(apsm).checkAvailability(Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class));
        Mockito.verify(fm).createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class),
                Mockito.anyString(),
                Mockito.any(BigDecimal.class));
        Mockito.verify(fm).add(Mockito.any(Flight.class));
        Mockito.verify(sm).getSeatIdsOfAirplane(Mockito.any(Airplane.class));
        Mockito.verify(fsm, Mockito.times(3)).createFlightSeat(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyBoolean());
        Mockito.verify(fsm, Mockito.times(3)).add(Mockito.any(FlightSeat.class));

    }

    @Test
    public void storeFlightExceptionTest(){
        values = CreateLogicHelper.getValues();
        cfl.setData(values);

        LocalDateTime depTime = cfl.makeDateTime("10", "30", "2022-10-10");
        LocalDateTime arrTime = cfl.makeDateTime("12", "45", "2022-10-11");

        Mockito.when(apm.getAirplane("X-WXYZ")).thenReturn(CreateLogicHelper.a4);
        Mockito.when(apsm.checkAvailability("X-WXYZ", depTime, arrTime)).thenReturn(true);
        Mockito.when(fm.createFlight(0,
                "AMS", "DUS",
                LocalDateTime.of(2022, 10, 10, 10, 30),
                LocalDateTime.of(2022, 10, 11, 12, 45),
                "X-WXYZ",
                new BigDecimal("120.00"))).thenReturn(CreateLogicHelper.f);
        Mockito.when(fm.add(CreateLogicHelper.f)).thenReturn(CreateLogicHelper.f);
        Mockito.when(sm.getSeatIdsOfAirplane(CreateLogicHelper.a4)).thenReturn(CreateLogicHelper.getSeatsId());
        Mockito.when(fsm.createFlightSeat(0, 0, true)).thenReturn(CreateLogicHelper.fs1);
        Mockito.when(fsm.createFlightSeat(1, 0, true)).thenReturn(CreateLogicHelper.fs2);
        Mockito.when(fsm.createFlightSeat(2, 0, true)).thenReturn(CreateLogicHelper.fs3);
        Mockito.when(fsm.add(CreateLogicHelper.fs1)).thenReturn(CreateLogicHelper.fs1);
        Mockito.when(fsm.add(CreateLogicHelper.fs2)).thenReturn(CreateLogicHelper.fs2);
        Mockito.when(fsm.add(CreateLogicHelper.fs3)).thenReturn(CreateLogicHelper.fs3);

        ThrowableAssert.ThrowingCallable code = () -> {
            cfl.storeFlight();
        };

        assertThatCode(code).doesNotThrowAnyException();

        Mockito.verify(apm).getAirplane(Mockito.anyString());
        Mockito.verify(apsm).checkAvailability(Mockito.anyString(), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class));
        Mockito.verify(fm).createFlight(
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class),
                Mockito.anyString(),
                Mockito.any(BigDecimal.class));
        Mockito.verify(fm).add(Mockito.any(Flight.class));
        Mockito.verify(sm).getSeatIdsOfAirplane(Mockito.any(Airplane.class));
        Mockito.verify(fsm, Mockito.times(3)).createFlightSeat(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyBoolean());
        Mockito.verify(fsm, Mockito.times(3)).add(Mockito.any(FlightSeat.class));

    }


}
