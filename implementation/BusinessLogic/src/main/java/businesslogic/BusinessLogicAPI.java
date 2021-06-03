package businesslogic;

import businessentitiesapi.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface BusinessLogicAPI {

    FlightManager getFlightManager();

    AirportManager getAirportManager();

    AirplaneManager getAirplaneManager();
    
    EditDetailsLogic getEditDetailsLogic();

    FlightRouteManager getFlightRouteManager();

    BookingManager getBookingsManager();

    TicketManager getTicketManager();
}
