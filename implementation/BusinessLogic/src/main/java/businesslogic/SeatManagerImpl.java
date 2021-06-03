package businesslogic;

import businessentitiesapi.FlightSeat;
import businessentitiesapi.Seat;
import businessentitiesapi.SeatManager;
import persistence.SeatStorageService;

import java.util.List;

public class SeatManagerImpl implements SeatManager {

    private SeatStorageService seatStorageService;

    public void setSeatStorageService(SeatStorageService seatStorageService) {
        this.seatStorageService = seatStorageService;
    }

    @Override
    public Seat createSeat(String seatTypeId, String seatNumber, String airplaneId) {
        //The 0 is just a placeholder
        return new Seat(0, seatTypeId, seatNumber, airplaneId);
    }

    @Override
    public Seat add(Seat s) {
        return seatStorageService.add(s);
    }

    @Override
    public List<Seat> getSeats() {
        return seatStorageService.getAll();
    }
}
