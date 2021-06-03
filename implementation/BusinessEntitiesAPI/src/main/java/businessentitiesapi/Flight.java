package businessentitiesapi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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
    private int flightID;
    private String originAirport;
    private String destinationAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airplaneModel;
    private BigDecimal basePrice;

    public Flight (
            int flightID,
            String originAirport,
            String destinationAirport,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            String airplane,
            BigDecimal basePrice
    ){
        List<String> exceptions = new ArrayList<>();

        this.flightID = flightID;

        // Check that the originAirport is set to the 3 letter code
        if (Pattern.matches("[A-Z]{3}", originAirport)) {
            this.originAirport = originAirport;
        } else {
            exceptions.add(
                    ResourceBundle.getBundle("businessentitiesapi.flightException", Locale.getDefault())
                            .getString("originAirportCode"));

        }

        // Check that the destinationAirport is set to 3 letter code and
        // is not the same as origin airport
        if (Pattern.matches("[A-Z]{3}", destinationAirport) && !originAirport.equals(destinationAirport)) {
             this.destinationAirport = destinationAirport;
        } else {
             exceptions.add(
                    ResourceBundle.getBundle("businessentitiesapi.flightException", Locale.getDefault())
                            .getString("destinationAirport"));
        }

        // Check that the departure time is in the future
        if (depTime.isAfter(LocalDateTime.now())) {
            this.departureTime = depTime;
        }else{
            exceptions.add(
                    ResourceBundle.getBundle("businessentitiesapi.flightException", Locale.getDefault())
                            .getString("depTimePast"));
        }

        // Check that the arrival time lies after the departure time
        if (arrTime.isAfter(depTime)) {
            this.arrivalTime = arrTime;
        }else{
            exceptions.add(
                    ResourceBundle.getBundle("businessentitiesapi.flightException", Locale.getDefault())
                            .getString("arrTime"));

        }

        // Check that the airplane model only consists of letters and numbers and whitespaces
        if (Pattern.matches("[a-zA-Z_0-9\\s\\-]+", airplane)) {
            this.airplaneModel = airplane;
        }else{
            exceptions.add(
                    ResourceBundle.getBundle("businessentitiesapi.flightException", Locale.getDefault())
                            .getString("airplaneModel"));

        }

        // Check that the basePrice cannot be 0 or less
        if (basePrice.doubleValue() > 0) {
            this.basePrice = basePrice;
        }else{
            exceptions.add(
                    ResourceBundle.getBundle("businessentitiesapi.flightException", Locale.getDefault())
                            .getString("price"));
        }

        if (!exceptions.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", exceptions));
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
    
    public BigDecimal getBasePrice() {
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
        return "Airplane " + airplaneModel + " on flight " + flightID + "\n"
                + "departing from " + originAirport + " on " + departureTime.getDayOfMonth() + "." + departureTime.getMonth()
                + " at " + departureTime.getHour() + ":" + departureTime.getMinute() + "\n"
                + "arriving at " + destinationAirport + " on " + arrivalTime.getDayOfMonth() + "." + arrivalTime.getMonth()
                + " at " + arrivalTime.getHour() + ":" + arrivalTime.getMinute() + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (this.flightID != other.flightID) {
            return false;
        }
        if (!Objects.equals(this.originAirport, other.originAirport)) {
            return false;
        }
        if (!Objects.equals(this.destinationAirport, other.destinationAirport)) {
            return false;
        }
        if (!Objects.equals(this.airplaneModel, other.airplaneModel)) {
            return false;
        }
        if (!Objects.equals(this.departureTime, other.departureTime)) {
            return false;
        }
        if (!Objects.equals(this.arrivalTime, other.arrivalTime)) {
            return false;
        }
        if (!Objects.equals(this.basePrice, other.basePrice)) {
            return false;
        }
        return true;
    }

}
