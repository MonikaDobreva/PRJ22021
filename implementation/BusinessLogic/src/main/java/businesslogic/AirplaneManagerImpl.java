
package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;

import java.time.LocalDateTime;
import java.util.List;

import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;
import persistence.AirplaneStorageService;

/**
 *
 * @author Rachel
 */
public class AirplaneManagerImpl implements AirplaneManager{
    
private AirplaneStorageService airplaneStorageService;

    public void setAirplaneStorageService(AirplaneStorageService airplaneStorageService){
        this.airplaneStorageService = airplaneStorageService;
    }

    @Override
    public Airplane createAirplane(String name, String code, int amountSeats) {
        return new AirplaneImpl(name, code, amountSeats);
    }

    @Override
    public void add(Airplane a) {
        airplaneStorageService.add(a);
    }

    @Override
    public List<Airplane> getAirplanes() {
        return airplaneStorageService.getAll();
    }

    @Override
    public void delete(Airplane a) {
        airplaneStorageService.delete(a);
    }

    @Override
    public boolean checkAvailability(Airplane a, LocalDateTime departure, LocalDateTime arrival) throws IllegalArgumentException {
        LocalDateTimeRange r = LocalDateTimeRange.of(departure, arrival);
        List<LocalDateTimeRange> schedule = this.airplaneSchedule(a);
        for(LocalDateTimeRange range : schedule){
            if(r.meets(range) || r.overlaps(range)){
                throw new IllegalArgumentException("This Airplane is not available for the specified dates");
            }
        }
        return true;
    }

    @Override
    public List<LocalDateTimeRange> airplaneSchedule(Airplane a) {
        return airplaneStorageService.getSchedule(a);
    }

}
