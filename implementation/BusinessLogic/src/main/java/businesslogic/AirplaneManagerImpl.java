
package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;

import java.util.ArrayList;
import java.util.List;

import genericdao.dao.DAOFactory;
import persistence.AirplaneStorageService;

import static java.lang.String.format;

/**
 *
 * @author Rachel
 */
public class AirplaneManagerImpl implements AirplaneManager{
    
    private AirplaneStorageService airplaneStorageService;
    private DAOFactory daof;

    public void setAirplaneStorageService(AirplaneStorageService airplaneStorageService, DAOFactory pgdFactory){
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
            return daof.createDao(Airplane.class).getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
