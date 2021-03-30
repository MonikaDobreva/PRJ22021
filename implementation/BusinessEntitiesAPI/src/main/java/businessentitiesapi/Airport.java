package businessentitiesapi;


/**
 * Airport class, representing all available airports for creating flights.
 *
 * @author Alex Boening {@code a.boning@student.fontys.nl}
 */

public interface Airport {

    /**
     * Get the IATA code of an airport
     * @return IATA three-letter-code
     */
    String getIataCode();

    /**
     * Get the full name of the airport
     * @return airport name
     */
    String getFullName();

    /**
     * Get the country of the airport
     * @return country name
     */
    String getCountry();

    /**
     * Get the city of the airport
     * @return city name
     */
    String getCity();
}
