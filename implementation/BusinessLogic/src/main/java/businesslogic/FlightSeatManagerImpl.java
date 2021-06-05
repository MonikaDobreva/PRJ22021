package businesslogic;

import businessentitiesapi.FlightSeat;
import businessentitiesapi.FlightSeatManager;
import persistence.FlightSeatStorageService;

import java.util.ArrayList;
import java.util.List;

public class FlightSeatManagerImpl implements FlightSeatManager {

    private FlightSeatStorageService flightSeatStorageService;

    public void setFlightSeatStorageService(FlightSeatStorageService flightSeatStorageService) {
        this.flightSeatStorageService = flightSeatStorageService;
    }

    @Override
    public FlightSeat createFlightSeat(int seatId, int flightId, boolean available) {
        //The 0 is just a placeholder
        return new FlightSeat(0, seatId, flightId, available);
    }

    @Override
    public FlightSeat add(FlightSeat f) {return flightSeatStorageService.add(f); }

    @Override
    public List<FlightSeat> addAll(List<Integer> seatsId, int flightId) {
        List<FlightSeat> flightSeats = new ArrayList<>();

        for (int seatId : seatsId){
            flightSeats.add(this.createFlightSeat(seatId, flightId, true));
        }

        return flightSeatStorageService.addAll(flightSeats);
    }

    @Override
    public List<FlightSeat> getFlightSeats() { return flightSeatStorageService.getAll(); }


}
