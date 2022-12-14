package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.Seat;
import businessentitiesapi.SeatManager;
import genericdao.dao.DAOFactory;
import persistence.SeatStorageService;

import java.util.List;
import java.util.stream.Collectors;

public class SeatManagerImpl implements SeatManager {

    private SeatStorageService seatStorageService;
    private DAOFactory daof;

    public void setSeatStorageService(SeatStorageService seatStorageService, DAOFactory daof) {
        this.seatStorageService = seatStorageService;
        this.daof = daof;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        daof = pgdFactory;
    }

    @Override
    public Seat createSeat(int seatTypeId, String seatNumber, int airplaneId) {
        //The 0 is just a placeholder
        return new Seat(0, seatTypeId, seatNumber, airplaneId);
    }

    @Override
    public Seat add(Seat s) {
        try {
            return daof.createDao(Seat.class).save(s).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Seat> getSeats() {
            return daof.createDao(Seat.class).getAll();
    }

    @Override
    public List<Integer> getSeatIdsOfAirplane(Airplane ap) {

        try {
            return daof.createDao(Seat.class).anyQuery(
                    "select * from seatsView where seatsView.airplaneID = ? ",
                    ap.getAirplaneID()
                    ).stream().map(Seat::getSeatId).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Seat getSeatForFlightSeat(FlightSeat flightSeat) {
        try{
            return daof.createDao(Seat.class).get(flightSeat.getSeatId()).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
