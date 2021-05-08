
package persistence;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Flight;
import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rachel
 */
public class AirplaneStorageServiceImpl implements AirplaneStorageService{

    private final AirplaneManager airplaneManager;
    
    public AirplaneStorageServiceImpl(AirplaneManager airplaneManager) {
        this.airplaneManager = airplaneManager;
    }
    
    @Override
    public Airplane add(Airplane a) {
       //TODO: implement add airplane to db
        return null;
    }

    @Override
    public List<Airplane> getAll() {
       //TODO: implement get all the airplanes from db
        return null;
    }

    @Override
    public void delete(Airplane a) {
        //TODO: implement airplane delete from db
    }

    @Override
    public List<LocalDateTimeRange> getSchedule(Airplane a) {
        //TODO: implement create airplane schedule from db
        return null;
    }

}
