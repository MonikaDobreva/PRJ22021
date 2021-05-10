package businessentitiesapi;

import java.util.List;

public interface AirportManager {

    /**
     * Creates a new airport, fed with the name, code and amount of seats.
     *
     * @param airportID
     * @param iataCode      unique code to identify an airport like "ATX"
     * @param fullName      full name of the airport
     * @param country   name of the country
     * @param city      name of the city
     * @return          a new airport object
     */
    Airport createAirport(int airportID, String iataCode, String fullName, String country, String city);

    /**
     * Adds a new airport to the airportManager, which stores all the airport objects in a list
     *
     * @param f The airport to be added
     */
    void add(Airport f);

    /**
     * Get all airport which were previously added to the airportStorage
     *
     * @return a List of airport objects
     */
    List<Airport> getAirports();

    /**
     * Deletes the given airport from the AirportManager
     */
    void delete(Airport a);

}
