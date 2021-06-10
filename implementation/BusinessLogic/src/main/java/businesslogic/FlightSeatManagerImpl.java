package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.FlightSeatManager;
import businessentitiesapi.exceptions.FlightStorageException;
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
    public FlightSeat add(FlightSeat fs) throws FlightStorageException {
        try {
            DAO<FlightSeat, Integer> flightSeatDao = daof.createDao(FlightSeat.class);
            TransactionToken token = flightSeatDao.startTransaction();
            Optional<FlightSeat> storedFlightSeat = flightSeatDao.save(fs);
            if (fs.equals(storedFlightSeat.get())) {
                token.commit();
            } else {
                token.rollback();
                throw new Exception();
            }
            flightSeatDao.close();
            return storedFlightSeat.get();
        } catch (Exception e) {
            throw new FlightStorageException("Flight Seat could not be added :(");
        }
    }

    @Override
    public List<FlightSeat> getFlightSeats() {
        try {
            return daof.createDao(FlightSeat.class).getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<FlightSeat> addAll(List<Integer> seatsId, int flightId) {
//        List<FlightSeat> flightSeats = new ArrayList<>();
//
//        for (int seatId : seatsId){
//            flightSeats.add(this.createFlightSeat(seatId, flightId, true));
//        }
//
////        return flightSeatStorageService.addAll(flightSeats);
//
//        try {
//            DAO<FlightSeat, Integer> flightSeatDao = daof.createDao(FlightSeat.class);
//            TransactionToken token = flightSeatDao.startTransaction();
//            var storedFlightSeats = flightSeatDao.saveAll(flightSeats);
//            token.commit();
//            flightSeatDao.close();
//            return new ArrayList<>(storedFlightSeats);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public List<FlightSeat> getAvailableFlightSeats(Flight flight, String seatType) {
        var query = "SELECT *\n" +
                "FROM flightSeatsView\n" +
                "         JOIN seatsview s on flightseatsview.seatid = s.seatid\n" +
                "         JOIN seattypesview s2 on s.seattypeid = s2.seattypeid\n" +
                "WHERE flightId = ?\n" +
                "  AND available = true\n" +
                "  AND s2.name = ?;";
        return daof.createDao(FlightSeat.class).anyQuery(query, flight.getFlightID(), seatType);
    }

    @Override
    public FlightSeat getFromSeatId(int id) {
        var dao =  daof.createDao(FlightSeat.class);
        return dao.getByColumnValues("seatId", id).get(0);
    }

}
