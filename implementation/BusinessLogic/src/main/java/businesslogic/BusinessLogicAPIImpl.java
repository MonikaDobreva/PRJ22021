package businesslogic;

import businessentitiesapi.*;
import genericdao.dao.DAOFactory;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;
import persistence.PersistenceAPI;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 * @author Rachel
 */
public class BusinessLogicAPIImpl implements BusinessLogicImplementationProvider, BusinessLogicAPI {

    final PersistenceAPI persistenceAPI;
    final DAOFactory daof;

    BusinessLogicAPIImpl(PersistenceAPI persistenceAPI, DAOFactory pgdFactory) {
        this.persistenceAPI = persistenceAPI;
        this.daof = pgdFactory;
    }

     
//    BusinessLogicAPIImpl() {
//        var ds = PGJDBCUtils.getDataSource("postgres");
//        daof = new PGDAOFactory(ds);
//    }
    
     
     
     @Override
    public FlightManager getFlightManager() {
        FlightManagerImpl flightManager = new FlightManagerImpl();
        flightManager.setFlightStorageService(persistenceAPI.getFlightStorageService(flightManager),daof);
        return flightManager;
    }

//    @Override
//    public FlightManager getFlightManager() {
//        FlightManagerImpl flightManager = new FlightManagerImpl();
//        flightManager.setFlightStorageService(persistenceAPI.getFlightStorageService(flightManager));
//        return flightManager;
//    }

    public AirportManager getAirportManager() {
        AirportManagerImpl airportManager = new AirportManagerImpl();
        airportManager.setAirportStorageService(persistenceAPI.getAirportStorageService(airportManager), daof);
        return airportManager;
    }

    public AirplaneManager getAirplaneManager() {
        AirplaneManagerImpl airplaneManager = new AirplaneManagerImpl();
        airplaneManager.setAirplaneStorageService(persistenceAPI.getAirplaneStorageService(airplaneManager), daof);
        return airplaneManager;
    }

    @Override
    public EditDetailsLogic getEditDetailsLogic() {
        EditDetailsLogic edl = new EditDetailsLogic(getFlightManager(),getFlightRouteManager());
        return edl;
    }

    @Override
    public FlightRouteManager getFlightRouteManager() {
        FlightRouteManagerImpl flightRouteManager = new FlightRouteManagerImpl();
        flightRouteManager.setFlightRouteStorageService(persistenceAPI.getFlightRouteStorageService(flightRouteManager), daof);
        return flightRouteManager;
    }

    @Override
    public BookingManager getBookingsManager() {
        BookingManagerImpl bookingManager = new BookingManagerImpl();
        bookingManager.setBookingStorageService(persistenceAPI.getBookingStorageService(bookingManager));
        return bookingManager;
    }

    @Override
    public TicketManager getTicketManager() {
        TicketManagerImpl ticketManager = new TicketManagerImpl();
        ticketManager.setTicketStorageService(persistenceAPI.getTicketStorageService(ticketManager), daof);
        return ticketManager;
    }

    @Override
    public FlightSeatManager getFlightSeatManager() {
        FlightSeatManagerImpl flightSeatManager = new FlightSeatManagerImpl();
        flightSeatManager.setFlightSeatStorageService(persistenceAPI.getFlightSeatStorageService(flightSeatManager), daof);
        return flightSeatManager;
    }

    @Override
    public SeatManager getSeatManager() {
        SeatManagerImpl seatManager = new SeatManagerImpl();
        seatManager.setSeatStorageService(persistenceAPI.getSeatStorageService(seatManager), daof);
        return seatManager;
    }

    @Override
    public MealTypeManager getMealTypeManager() {
        MealTypeManagerImpl mealTypeManager = new MealTypeManagerImpl();
        mealTypeManager.setMealTypeStorageService(persistenceAPI.getMealTypeStorageService(mealTypeManager));
        return mealTypeManager;
    }

    @Override
    public AirplaneScheduleManager getAirplaneScheduleManager(){
        AirplaneScheduleManagerImpl airplaneScheduleManager = new AirplaneScheduleManagerImpl();
        airplaneScheduleManager.setAirplaneScheduleStorageService(null, daof);
        return airplaneScheduleManager;
    }

    @Override
    public PersonManager getPersonManager() {
        PersonManagerImpl personManager = new PersonManagerImpl();
        personManager.setPersonStorageService(persistenceAPI.getPersonManagerStorageService(personManager));
        return personManager;
    }

    @Override
    public PassengerManager getPassengerManager() {
        PassengerManagerImpl passengerManager = new PassengerManagerImpl();
        passengerManager.setPassengerStorageService(persistenceAPI.getPassengerManagerStorageService(passengerManager));
        return passengerManager;
    }

}
