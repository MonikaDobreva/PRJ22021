package persistence;

import businessentitiesapi.*;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class PersistenceAPIImpl implements PersistenceAPI, PersistenceImplementationProvider {

    @Override
    public FlightStorageService getFlightStorageService(FlightManager flightManager){
        return new FlightStorageServiceImpl(flightManager);
    }

    @Override
    public AirplaneStorageService getAirplaneStorageService(AirplaneManager airplaneManager){
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



}
