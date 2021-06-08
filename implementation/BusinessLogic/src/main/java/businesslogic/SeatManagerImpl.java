package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.Seat;
import businessentitiesapi.SeatManager;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import persistence.SeatStorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SeatManagerImpl implements SeatManager {

    private SeatStorageService seatStorageService;
    private DAOFactory daof;

    public void setSeatStorageService(SeatStorageService seatStorageService, DAOFactory daof) {
        this.seatStorageService = seatStorageService;
        this.daof = daof;
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
    public List<Integer> getSeatIdsOfAirplane(Airplane ap) {

        try {
            return daof.createDao(Seat.class).anyQuery("select * from seatsView " +
                            "where seatsView.airplaneID = ? ",
                    ap.getAirplaneID()).stream().map(Seat::getSeatId).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

//        List<Integer> seatIds = new ArrayList<>();
//        List<Seat> seats = seatStorageService.getSeatsOfAirplane(a);
//
//        for(Seat seat : seats){
//            seatIds.add(seat.getSeatId());
//        }
//
//        return seatIds;

    }

    @Override
    public Seat getSeatForFlightSeat(FlightSeat flightSeat) {
        return seatStorageService.getSeatForFlightSeat(flightSeat);
    }
}
