
package persistence;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Flight;
import genericdao.dao.DAOException;
import genericdao.pgdao.PGDAO;
import genericdao.pgdao.PGJDBCUtils;
import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;

import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 *
 * @author Rachel
 */
public class AirplaneStorageServiceImpl implements AirplaneStorageService{

    private final AirplaneManager airplaneManager;
    private final DataSource ds;
    
    public AirplaneStorageServiceImpl(AirplaneManager airplaneManager) {
        this.airplaneManager = airplaneManager;
        ds = PGJDBCUtils.getDataSource("postgres");
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
        List<LocalDateTimeRange> schedule = new ArrayList<>();
        String sql = format(
                "select %1$s from %2$s where (%3$s)=(?)",
                "(airplaneCode, departureTime, arrivalTime)",
                "airplaneSchedule",
                "airplaneCode" );
        try (Connection con = ds.getConnection();
             PreparedStatement pst = con.prepareStatement( sql );){

            ResultSet result = pst.executeQuery();

            while(result.next()){
                LocalDateTime departureTime = result.getObject("departureTime", LocalDateTime.class);
                LocalDateTime arrivalTime = result.getObject("arrivalTime", LocalDateTime.class);

                LocalDateTimeRange r = LocalDateTimeRange.of(departureTime, arrivalTime);
                schedule.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return schedule;
    }

}
