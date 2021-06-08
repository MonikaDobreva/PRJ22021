
package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;
import persistence.AirplaneStorageService;

/**
 *
 * @author Rachel
 */
public class AirplaneManagerImpl implements AirplaneManager{
    
    private AirplaneStorageService airplaneStorageService;
    private DAOFactory daof;

    public void setAirplaneStorageService(AirplaneStorageService airplaneStorageService){
        this.airplaneStorageService = airplaneStorageService;
        this.daof = daof;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        this.daof = pgdFactory;
    }

    @Override
    public Airplane createAirplane(int ID ,String name, String code, int amountSeats) {
        return new Airplane(ID, name, code, amountSeats);
    }

    @Override
    public Airplane getAirplane(String airplaneCode) {
        return this.getAirplanes().stream().filter(a -> a.getAirplaneCode().equals(airplaneCode)).findFirst().get();
    }

    @Override
    public List<Airplane> getAirplanes() {
        try {
            return new ArrayList<>(daof.createDao(Airplane.class).getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkAvailability(Airplane a, LocalDateTime departure, LocalDateTime arrival) throws IllegalArgumentException {
        LocalDateTimeRange r = LocalDateTimeRange.of(departure, arrival);
        List<LocalDateTimeRange> schedule = this.airplaneSchedule(a);
        for(LocalDateTimeRange range : schedule){
            if(r.meets(range) || r.overlaps(range)){
                throw new IllegalArgumentException("The Airplane selected is not available for the specified dates");
            }
        }
        return true;
    }

    @Override
    public List<LocalDateTimeRange> airplaneSchedule(Airplane a) {
        return airplaneStorageService.getSchedule(a);
    }

}
