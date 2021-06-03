package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.FlightSeatManager;
import persistence.FlightSeatStorageService;

import java.util.List;

public class FlightSeatManagerImpl implements FlightSeatManager {

    private FlightSeatStorageService flightSeatStorageService;

    public void setFlightSeatStorageService(FlightSeatStorageService flightSeatStorageService) {
        this.flightSeatStorageService = flightSeatStorageService;
    }

    @Override
    public FlightSeat createFlightSeat(String seatId, String flightId, boolean available) {
        //The 0 is just a placeholder
        return new FlightSeat(0, seatId, flightId, available);
    }

    @Override
    public FlightSeat add(FlightSeat f) {return flightSeatStorageService.add(f); }

    @Override
    public List<FlightSeat> getFlightSeats() { return flightSeatStorageService.getAll(); }

}
