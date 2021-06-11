package businessentitiesapi;


import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

/**
 * Airport class, representing all available airports for creating flights.
 *
 * @author Alex Boening {@code a.boning@student.fontys.nl}
 */

@TableName("airportsView")
public class Airport implements Serializable {

    @ID
    private final int airportID;
    private final String iataCode; // IATA = International Air Transport Association
    private final String fullName;
    private final String city; //Make Locale later?
    private final String country;

    public Airport(int airportID, String iataCode, String fullName, String city, String country) {
        this.airportID = airportID;
        this.iataCode = iataCode;
        this.fullName = fullName;
        this.country = country;
        this.city = city;
    }

    /**
     * Get the ID of an airport
     * @return airportID
     */
    public int getAirportID() {
        return airportID;
    }

    /**
     * Get the IATA code of an airport
     * @return IATA three-letter-code
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * Get the full name of the airport
     * @return airport name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Get the country of the airport
     * @return country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get the city of the airport
     * @return city name
     */
    public String getCity() {
        return city;
    }
    
    @Override
    public String toString() {
        return "Airport with IATA code '" + iataCode + "' corresponding to '" + fullName +
                "' located in " + city + ", " + country;
    }
}
