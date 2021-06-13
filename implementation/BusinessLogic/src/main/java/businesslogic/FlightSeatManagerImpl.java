package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightSeat;
import businessentitiesapi.FlightSeatManager;
import genericdao.dao.DAOFactory;
import persistence.FlightSeatStorageService;

import java.util.ArrayList;
import java.util.List;

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
    public FlightSeat add(FlightSeat fs){
        try {
            return daof.createDao(FlightSeat.class).save(fs).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FlightSeat> getFlightSeats() {
            return daof.createDao(FlightSeat.class).getAll();
    }

    @Override
    public List<FlightSeat> getAvailableFlightSeats(Flight flight, String seatType) {
        var query =
                "SELECT *\n" +
                "FROM flightSeatsView\n" +
                "JOIN seatsview s on flightseatsview.seatid = s.seatid\n" +
                "JOIN seattypesview s2 on s.seattypeid = s2.seattypeid\n" +
                "WHERE flightId = ?\n" +
                "AND available = true\n" +
                "AND s2.name = ?;";
        return daof.createDao(FlightSeat.class).anyQuery(query, flight.getFlightID(), seatType);
    }

    @Override
    public FlightSeat getFromSeatId(int id) {
        var dao =  daof.createDao(FlightSeat.class);
        return dao.getByColumnValues("seatId", id).get(0);
    }

    @Override
    public void updateFlightSeatAvailability(boolean availability, int id) {
        flightSeatStorageService.updateFlightSeatAvailability(availability, id);
    }

}
