
package persistence;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Airport;
import businessentitiesapi.Flight;
import genericdao.dao.DAO;
import genericdao.dao.DAOException;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAO;
import genericdao.pgdao.PGDAOFactory;
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
import java.util.Collection;
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
    private final DAO<Airplane, Integer> airplaneDao;
    private final DataSource ds;
    
    public AirplaneStorageServiceImpl(AirplaneManager airplaneManager) {
        this.airplaneManager = airplaneManager;
        ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        airplaneDao = daof.createDao(Airplane.class);
    }
    
    @Override
    public Airplane add(Airplane a) {
       //TODO: implement add airplane to db
        return null;
    }

    @Override
    public List<Airplane> getAll() {
        try {
            TransactionToken tok = airplaneDao.startTransaction();
            Collection<Airplane> all = airplaneDao.getAll();
            airplaneDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Airplane a) {
        //TODO: implement airplane delete from db
    }

    @Override
    public List<LocalDateTimeRange> getSchedule(Airplane a) {

        List<LocalDateTimeRange> schedule = new ArrayList<>();
        String sql = format(
                "select %1$s from %2$s where %3$s = ?",
                "airplaneCode, departureTime, arrivalTime",
                "airplaneSchedule",
                "airplaneCode");
        try (Connection con = ds.getConnection();
             PreparedStatement pst = con.prepareStatement( sql);){

            pst.setObject(1, a.getAirplaneCode());
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
