package persistence;

import businessentitiesapi.*;
import genericdao.dao.DAOFactory;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface PersistenceAPI {

    /**
     * Get FlightStorageService
     * Provides a storage object that knows how to store and retrieve flights.
     * Implemented by a default method to enable the creation of customized
     * PersistenceFacade implementations with limited services, for test purposes.
     *
     * @param flightManager -
     * @return FlightStorageService object that knows how to store and retrieve flights.
     */
    default FlightStorageService getFlightStorageService(FlightManager flightManager){
        return null;
    }

    default AirportStorageService getAirportStorageService(AirportManager airportManager){
        return null;
    }

    default AirplaneStorageService getAirplaneStorageService(AirplaneManager airplaneManager, DAOFactory daof){
        return null;
    }

    default FlightRouteStorageService getFlightRouteStorageService(FlightRouteManager flightRouteManager){
        return null;
    }

    default BookingStorageService getBookingStorageService(BookingManager bookingManager){
        return null;
    };

    default TicketStorageService getTicketStorageService(TicketManager ticketManager){ return null;};

    default FlightSeatStorageService getFlightSeatStorageService(FlightSeatManager flightSeatManager){ return null;};

    default SeatStorageService getSeatStorageService(SeatManager seatManager){ return null;};

    default MealTypeStorageService getMealTypeStorageService(MealTypeManager mealTypeManager) {return null;};

    default AirplaneStorageService getAirplaneStorageService(AirplaneManager airplaneManager) {return null;};

    // This interface can be extended with all services that need to be made
    // available to the business logic

}
