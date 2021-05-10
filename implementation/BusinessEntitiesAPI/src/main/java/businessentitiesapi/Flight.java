package businessentitiesapi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;
import nl.fontys.sebivenlo.sebiannotations.*;

/**
 * Flight class, representing the flight of the airline company.
 *
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */
@TableName("flightsView")
public class Flight implements Serializable {
    
    @ID
    private final int flightID;
    private final String originAirport;
    private final String destinationAirport;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final String airplaneModel;
    private final int basePrice;

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

        /**
         * this checks that the originAirport is set to the 3 letter code
         */
        if (Pattern.matches("[A-Z]{3}", originAirport)) {
            this.originAirport = originAirport;
        } else {
            throw new IllegalArgumentException("Is not 3 Uppercase letter code!");
        }

        /**
         * this checks that the destinationAirport is set to 3 letter code and
         * is not the same as origin airport
         */
        if (Pattern.matches("[A-Z]{3}", destinationAirport) && !originAirport.equals(destinationAirport)) {
             this.destinationAirport = destinationAirport;
        } else {
            throw new IllegalArgumentException("Is not 3 Uppercase letter code! Or is same Airport as departure airport!");
        }
        
        /**
         * this checks that the departure time is in the future
         */
        if (depTime.isAfter(LocalDateTime.now())) {
            this.departureTime = depTime;
        }else{
            throw new IllegalArgumentException("The departure time lies in the past!");
        }
        
        /**
         * this checks that the arrival time lies after the departure time
         */
        if (arrTime.isAfter(depTime)) {
             this.arrivalTime = arrTime;
        }else{
            throw new IllegalArgumentException("The arrival time lies before the departure time!");
        }
        
        /**
         * this checks that the airplane model only consists of letters and numbers and whitespaces
         */
        if (Pattern.matches("[a-zA-Z/0-9\\s]+", airplane)) {
            this.airplaneModel = airplane;
        }else{
            throw new IllegalArgumentException("The name is consisting of more than whitespaces,letters and numbers!");
        }
        
        /**
         * this checks that the baseprice cannot be 0 or less
         */
        if (basePrice>0) {
            this.basePrice = basePrice; 
        }else{
           throw new IllegalArgumentException("The price is less than zero!"); 
        }
        
    }
    
    public int getFlightID() {
        return flightID;
    }
    
    public String getOriginAirport() {
        return originAirport;
    }
    
    public String getDestinationAirport() {
        return destinationAirport;
    }
    
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    public String getAirplane() {
        return airplaneModel;
    }
    
    public int getBasePrice() {
        return basePrice;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    // Airplane ... on flight ...
    // departing from ... on DAY.MONTH at HH:mm
    // arriving at ... on DAY.MONTH at HH:mm
    @Override
    public String toString() {
        return "Airplane " + airplaneModel + " on flight " + getFlightID() + "\n"
                + "departing from " + originAirport + " on " + departureTime.getDayOfMonth() + "." + departureTime.getMonth()
                + " at " + departureTime.getHour() + ":" + departureTime.getMinute() + "\n"
                + "arriving at " + destinationAirport + " on " + arrivalTime.getDayOfMonth() + "." + arrivalTime.getMonth()
                + " at " + arrivalTime.getHour() + ":" + arrivalTime.getMinute() + "\n";
    }
    
}
