package persistence;

import businessentitiesapi.*;
import genericdao.dao.DAOFactory;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class PersistenceAPIImpl implements PersistenceAPI, PersistenceImplementationProvider {

    @Override
    public FlightStorageService getFlightStorageService(FlightManager flightManager){
        return new FlightStorageServiceImpl(flightManager);
    }

    @Override
    public AirplaneStorageService getAirplaneStorageService(AirplaneManager airplaneManager, DAOFactory daof){
        return new AirplaneStorageServiceImpl(airplaneManager);
    }

    @Override
    public AirportStorageService getAirportStorageService(AirportManager airportManager){
        return new AirportStorageServiceImpl(airportManager);
    }
    @Override
    public FlightRouteStorageService getFlightRouteStorageService(FlightRouteManager flightRouteManager){
        return new FlightRouteStorageServiceImpl(flightRouteManager);
    }
    @Override
    public BookingStorageService getBookingStorageService(BookingManager bookingManager){
        return new BookingStorageServiceImpl(bookingManager);
    }

    @Override
    public TicketStorageService getTicketStorageService(TicketManager ticketManager){
        return new TicketStorageServiceImpl(ticketManager);
    }

    @Override
    public FlightSeatStorageService getFlightSeatStorageService(FlightSeatManager flightSeatManager) {
        return new FlightSeatStorageServiceImpl(flightSeatManager);
    }

    @Override
    public SeatStorageService getSeatStorageService(SeatManager seatManager) {
        return new SeatStorageServiceImpl();
    }

    @Override
    public MealTypeStorageService getMealTypeStorageService(MealTypeManager mealTypeManager) {
        return new MealTypeStorageServiceImpl(mealTypeManager);
    }

    @Override
    public PersonStorageService getPersonManagerStorageService(PersonManager personManager) {
        return new PersonStorageServiceImpl();
    }

    @Override
    public PassengerStorageService getPassengerManagerStorageService(PassengerManager passengerManager) {
        return new PassengerStorageServiceImpl();
    }
}
