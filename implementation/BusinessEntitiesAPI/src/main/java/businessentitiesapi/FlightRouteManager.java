package businessentitiesapi;

import businessentitiesapi.exceptions.FlightStorageException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRouteManager {

    /**
     * Creates a new flight, fed with the name, departure and arrival date.
     *
     * @param flightRouteID             The name of the flight route
     * @param originAirportCode       The airport where the flight takes off
     * @param destinationAirportCode  The airport where the flight lands
     *
     * @return a new flight object
     */
    FlightRoute createFlightRoute(
            int flightRouteID,
            String originAirportCode,
            String destinationAirportCode
    );

    /**
     * Adds a new flight to the FlightManager, which stores all the flight objects in a list
     *
     * @param fr The flight route to be added
     * @return the added flight route
     */
    FlightRoute add(FlightRoute fr) throws FlightStorageException;

    /**
     * Get all flight routes
     * @return a List of flight routes objects
     */
    List<FlightRoute> getFlightRoutes();

    /**
     * Check if a flight Route containing the given airports exists, if not creates it
     *
     * @param originAirport       The airport where the flight takes off
     * @param destinationAirport  The airport where the flight lands
     * @return
     */
    void checkExistence(String originAirport, String destinationAirport) throws FlightStorageException;

    //List<Flight> getFlightsByRouteId(int routeID);
}
