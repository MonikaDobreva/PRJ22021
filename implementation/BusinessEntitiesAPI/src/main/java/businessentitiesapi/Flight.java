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

@TableName("flights_view")
public class Flight implements Serializable {

    @ID
    private final int flightID;
    private final String originAirport;
    private final String destinationAirport;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final String airplaneModel;
    private final int basePrice;
    // The airplane and airports will be implemented as own classes later!
    // For now they are just Strings. You can delete comment when implemented.

    public Flight(
            int flightID,
            String originAirport,
            String destinationAirport,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            String airplane,
            int basePrice
    ) {
        this.flightID = flightID;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = depTime;
        this.arrivalTime = arrTime;
        this.airplaneModel = airplane;
        this.basePrice = basePrice;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getOriginAirport(){ return originAirport; }

    public String getDestinationAirport(){ return destinationAirport; }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getAirplane() { return airplaneModel; }

    public int getBasePrice() {
        return basePrice;
    }
 
    // Airplane ... on flight ...
    // departing from ... on DAY.MONTH at HH:mm
    // arriving at ... on DAY.MONTH at HH:mm

    @Override
    public String toString() {
        return "Airplane " + airplaneModel + " on flight " + getFlightID() + "\n" +
               "departing from " + originAirport + " on " + departureTime.getDayOfMonth() + "." + departureTime.getMonth() +
                " at " + departureTime.getHour() + ":" + departureTime.getMinute() + "\n" +
                "arriving at " + destinationAirport + " on " + arrivalTime.getDayOfMonth() + "." + arrivalTime.getMonth() +
                " at " + arrivalTime.getHour() + ":" + arrivalTime.getMinute() + "\n";
    }

}