
package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import java.util.List;
import persistence.AirplaneStorageService;

/**
 *
 * @author Rachel
 */
public class AirplaneManagerImpl implements AirplaneManager{
    
private AirplaneStorageService airplaneStorageService;

    public void setFlightStorageService(AirplaneStorageService airplaneStorageService){
        this.airplaneStorageService = airplaneStorageService;
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
    public Airplane createAirplane(String name, String code, int amountSeats) {
         return new AirplaneImpl(name, code,amountSeats);
    }
   
}
