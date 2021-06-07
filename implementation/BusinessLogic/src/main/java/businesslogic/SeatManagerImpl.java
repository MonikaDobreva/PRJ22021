package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.Seat;
import businessentitiesapi.SeatManager;
import persistence.SeatStorageService;

import java.util.ArrayList;
import java.util.List;

public class SeatManagerImpl implements SeatManager {

    private SeatStorageService seatStorageService;

    public void setSeatStorageService(SeatStorageService seatStorageService) {
        this.seatStorageService = seatStorageService;
    }

    @Override
    public Seat createSeat(int seatTypeId, String seatNumber, int airplaneId) {
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

    @Override
    public List<Integer> getSeatIdsOfAirplane(Airplane a) {
        List<Integer> seatIds = new ArrayList<>();
        List<Seat> seats = seatStorageService.getSeatsOfAirplane(a);

        for(Seat seat : seats){
            seatIds.add(seat.getSeatId());
        }

        return seatIds;
    }

    @Override
    public Seat getSeatForFlightSeat(FlightSeat flightSeat) {
        return seatStorageService.getSeatForFlightSeat(flightSeat);
    }
}
