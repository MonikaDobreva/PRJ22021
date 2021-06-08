package businesslogic;

import businessentitiesapi.*;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import persistence.SeatStorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
            DAO<Seat, Integer> seatDao = daof.createDao(Seat.class);
            TransactionToken tok = seatDao.startTransaction();
            Optional<Seat> storedSeat = seatDao.save(s);
            tok.commit();
            seatDao.close();
            return storedSeat.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Seat> getSeats() {
        try {
            return daof.createDao(Seat.class).getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
