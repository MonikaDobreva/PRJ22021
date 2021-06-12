package businesslogic.testHelpers;

import businessentitiesapi.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateLogicHelper {

    public static Flight f1 = new Flight(0,
            "DUS", "YVY",
            LocalDateTime.of(2021, 12, 3, 10, 30),
            LocalDateTime.of(2021, 12, 3, 15, 30),
            "V-BBBB",
            new BigDecimal("100.50"));

    public static Flight f2 = new Flight(0,
            "DUS", "BCN",
            LocalDateTime.of(2021, 10, 5, 9, 45),
            LocalDateTime.of(2021, 10, 6, 6, 0),
            "A-RRRR",
            new BigDecimal("200.00"));

    public static Flight f3 = new Flight(0,
            "DUS", "AMS",
            LocalDateTime.of(2021, 8, 15, 9, 45),
            LocalDateTime.of(2021, 8, 15, 12, 0),
            "E-EEEE",
            new BigDecimal("300.00"));

    public static Flight f = new Flight(0,
            "AMS", "DUS",
            LocalDateTime.of(2022, 10, 10, 10, 30),
            LocalDateTime.of(2022, 10, 11, 12, 45),
            "X-WXYZ",
            new BigDecimal("120.00"));

    public static Airport ap1 = new Airport(0, "AMS", "Amsterdam", "Netherlands", "Amsterdam");
    public static Airport ap2 = new Airport(0, "BCN", "Barcelona", "Spain", "Barcelona");
    public static Airport ap3 = new Airport(0, "DUS", "Düsseldorf", "Germany", "Düsseldorf");

    public static Airplane a1 = new Airplane(0, "Boeing 377","V-BBBB",367);
    public static Airplane a2 = new Airplane(1, "Boeing 350","V-AAAA",250);
    public static Airplane a3 = new Airplane(2, "Boeing 250","V-CCCC",20);
    public static Airplane a4 = new Airplane(0, "Mini 26-Seater","X-WXYZ",20);

    public static Seat s1 = new Seat(0, 4, "1A", 1);
    public static Seat s2 = new Seat(1, 4, "1B", 1);
    public static Seat s3 = new Seat(2, 3, "1C", 1);
    public static Seat s4 = new Seat(3, 3, "1D", 1);
    public static Seat s5 = new Seat(4, 2, "1F", 1);
    public static Seat s6 = new Seat(5, 1, "1G", 1);

    public static FlightSeat fs1 = new FlightSeat(0, 0, 1, true);
    public static FlightSeat fs2 = new FlightSeat(0, 1, 1, true);
    public static FlightSeat fs3 = new FlightSeat(0, 2, 1, true);

    public static Map<String, String> getValues(){
        Map<String, String> values = new HashMap<>();

        values.put("flightID", "0");
        values.put("originAirport", "AMS");
        values.put("destinationAirport", "DUS");
        values.put("dTHour", "10");
        values.put("dTMin", "30");
        values.put("dTDate", "2022-10-10");
        values.put("aTHour", "12");
        values.put("aTMin", "45");
        values.put("aTDate", "2022-10-11");
        values.put("airplaneInfo", "X-WXYZ (Mini 26-Seater)");
        values.put("price", "120.00");

        return values;
    }

    public static Map<String, String> getValues(String... v){
        Map<String, String> values = new HashMap<>();

        values.put("flightID", v[0]);
        values.put("originAirport", v[1]);
        values.put("destinationAirport", v[2]);
        values.put("dTHour", v[3]);
        values.put("dTMin", v[4]);
        values.put("dTDate", v[5]);
        values.put("aTHour", v[6]);
        values.put("aTMin", v[7]);
        values.put("aTDate", v[8]);
        values.put("airplaneInfo", v[9]);
        values.put("price", v[10]);

        return values;
    }

    public static List<Flight> getFlights(){
        List<Flight> flights = new ArrayList<>();

        flights.add(f1);
        flights.add(f2);
        flights.add(f3);

        return flights;
    }

    public static List<Airport> getAirports(){
        List<Airport> airports = new ArrayList<>();

        airports.add(ap1);
        airports.add(ap2);
        airports.add(ap3);

        return airports;
    }

    public static List<Airplane> getAirplanes(){
        List<Airplane> airplanes = new ArrayList<>();

        airplanes.add(a1);
        airplanes.add(a2);
        airplanes.add(a3);

        return airplanes;
    }

    public static List<Seat> getSeats(){
        List<Seat> seats = new ArrayList<>();

        seats.add(s1);
        seats.add(s2);
        seats.add(s3);

        return seats;
    }

    public static List<Integer> getSeatsId(){
        List<Integer> list = new ArrayList<>();

        list.add(0);
        list.add(1);
        list.add(2);

        return list;
    }
}
