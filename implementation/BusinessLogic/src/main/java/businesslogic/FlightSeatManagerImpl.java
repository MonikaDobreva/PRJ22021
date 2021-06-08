package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.FlightSeatManager;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import persistence.FlightSeatStorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightSeatManagerImpl implements FlightSeatManager {

    private FlightSeatStorageService flightSeatStorageService;
    private DAOFactory daof;

    public void setFlightSeatStorageService(FlightSeatStorageService flightSeatStorageService, DAOFactory daof) {
        this.flightSeatStorageService = flightSeatStorageService;
        this.daof = daof;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        daof = pgdFactory;
    }

    @Override
    public FlightSeat createFlightSeat(int seatId, int flightId, boolean available) {
        //The 0 is just a placeholder
        return new FlightSeat(0, seatId, flightId, available);
    }

    @Override
    public FlightSeat add(FlightSeat fs) {
        try {
            DAO<FlightSeat, Integer> flightSeatDao = daof.createDao(FlightSeat.class);
            TransactionToken token = flightSeatDao.startTransaction();
            Optional<FlightSeat> storedFlightSeat = flightSeatDao.save(fs);
            token.commit();
            flightSeatDao.close();
            return storedFlightSeat.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FlightSeat> getFlightSeats() {
        try {
            return new ArrayList<>(daof.createDao(FlightSeat.class).getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FlightSeat> addAll(List<Integer> seatsId, int flightId) {
        List<FlightSeat> flightSeats = new ArrayList<>();

        for (int seatId : seatsId){
            flightSeats.add(this.createFlightSeat(seatId, flightId, true));
        }

//        return flightSeatStorageService.addAll(flightSeats);

        try {
            DAO<FlightSeat, Integer> flightSeatDao = daof.createDao(FlightSeat.class);
            TransactionToken token = flightSeatDao.startTransaction();
            var storedFlightSeats = flightSeatDao.saveAll(flightSeats);
            token.commit();
            flightSeatDao.close();
            return new ArrayList<>(storedFlightSeats);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FlightSeat> getAvailableFlightSeats(Flight flight, String seatType) {
        return flightSeatStorageService.findAvailableSeatsForFlight(flight, seatType);
    }

}
