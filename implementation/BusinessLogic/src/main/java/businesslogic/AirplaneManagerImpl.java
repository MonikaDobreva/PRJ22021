
package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import genericdao.dao.DAOFactory;
import persistence.AirplaneStorageService;

import java.util.List;

/**
 * @author Rachel
 */
public class AirplaneManagerImpl implements AirplaneManager {

    private AirplaneStorageService airplaneStorageService;
    private DAOFactory daof;

    public void setAirplaneStorageService(AirplaneStorageService airplaneStorageService, DAOFactory pgdFactory) {
        this.airplaneStorageService = airplaneStorageService;
        this.daof = pgdFactory;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        this.daof = pgdFactory;
    }

    @Override
    public Airplane createAirplane(int ID, String name, String code, int amountSeats) {
        return new Airplane(ID, name, code, amountSeats);
    }

    @Override
    public Airplane getAirplane(String airplaneCode) {
        return this.getAirplanes().stream().filter(a -> a.getAirplaneCode().equals(airplaneCode)).findFirst().get();
    }

    @Override
    public List<Airplane> getAirplanes() {
        return daof.createDao(Airplane.class).getAll();
    }

    @Override
    public Airplane getBiggestPlane() {
        String query =
                "select * " +
                "from airplanes " +
                "order by capacity desc " +
                "LIMIT 1;";
        return daof.createDao(Airplane.class).anyQuery(query).get(0);
    }

}
