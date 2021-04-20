package businessentitiesapi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import nl.fontys.sebivenlo.sebiannotations.*;

/**
 * Flight class, representing the flight of the airline company.
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
*/

@TableName("flights")
public class Flight implements Serializable {

    @ID
    private final int id;
    private final LocalDateTime departure_time;
    private final LocalDateTime arrival_time;
    private final  int airplane_id;
    private final int flight_route_id;
    private final  int base_price;
    // The airplane and airports will be implemented as own classes later!
    // For now they are just Strings. You can delete comment when implemented.

    public Flight(
            int flightID,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            int airplane,
             int flightRouteId,
            int basePrice
           
    ) {
        this.id = flightID;
        this.departure_time = depTime;
        this.arrival_time = arrTime;
        this.airplane_id = airplane;
        this.flight_route_id =flightRouteId;
        this.base_price = basePrice;
    }


    public int getFlightID() {
        return id;
    }


    public LocalDateTime getDepartureTime() {
        return departure_time;
    }


    public LocalDateTime getArrivalTime() {
        return arrival_time;
    }


    public int getAirplane() { return airplane_id; }

 
    // Airplane ... on flight ...
    // departing from ... on DAY.MONTH at HH:mm
    // arriving at ... on DAY.MONTH at HH:mm

    @Override
    public String toString() {
        return "Airplane " + airplane_id + " on flight \n"
               + arrival_time.getDayOfMonth() + "." + arrival_time.getMonth() + " at " + arrival_time.getHour() + ":" + arrival_time.getMinute() + "\n";
    }


    public int getFlightRouteID() {
      return flight_route_id;
    }


    public int getBasePrice() {
        return base_price;
    }

 
    
}

//public interface Flight extends Serializable {
//
//    /**
//     * databse id basically
//     * @return flightID
//     */
//    int getFlightID();
//    
//    /**
//     * Get the name of the flight
//     * @return flight name
//     */
//    String getName();
//
//    /**
//     * Get the departure time of the flight
//     * @return date and time of departure
//     */
//    LocalDateTime getDepartureTime();
//
//    /**
//     * Get the arrival time of the flight
//     * @return date and time of departure
//     */
//    LocalDateTime getArrivalTime();
//
//    /**
//     * Get the Airplane object of a flight
//     * For now only returns a string
//     * @return id of the plane
//     */
//    int getAirplane();
//
//    int getFlightRouteID();
//    
//    int getBasePrice();
//    
//    
//}