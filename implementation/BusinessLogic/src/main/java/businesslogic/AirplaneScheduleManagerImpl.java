package businesslogic;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneSchedule;
import businessentitiesapi.AirplaneScheduleManager;
import businessentitiesapi.Flight;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;
import persistence.AirplaneStorageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class AirplaneScheduleManagerImpl implements AirplaneScheduleManager {

    private DAOFactory daof;

    public void setAirplaneScheduleStorageService(AirplaneStorageService ass, DAOFactory daof){
        this.daof = daof;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        this.daof = pgdFactory;
    }

    @Override
    public List<AirplaneSchedule> getAirplaneSchedule(String airplaneCode) {
//        return airplaneStorageService.getSchedule(a);

        List<LocalDateTimeRange> schedule = new ArrayList<>();
        String sql = format(
                "select %1$s from %2$s where %3$s = ?",
                "airplaneCode, departureTime, arrivalTime",
                "airplaneSchedule",
                "airplaneCode");
        try {
            return daof.createDao(AirplaneSchedule.class).anyQuery(sql, airplaneCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LocalDateTimeRange> airplaneScheduleRange(String airplaneCode) {

        List<LocalDateTimeRange> schedule = new ArrayList<>();
        List<AirplaneSchedule> airplaneSchedule = this.getAirplaneSchedule(airplaneCode);

        for (AirplaneSchedule as : airplaneSchedule){
            schedule.add(LocalDateTimeRange.of(as.getDepartureTime(), as.getArrivalTime()));
        }

        return schedule;

    }

    @Override
    public boolean checkAvailability(String airplaneCode, LocalDateTime departure, LocalDateTime arrival) throws IllegalArgumentException {
          LocalDateTimeRange r = LocalDateTimeRange.of(departure, arrival);
          List<LocalDateTimeRange> schedule = this.airplaneScheduleRange(airplaneCode);
          for(LocalDateTimeRange range : schedule){
              if(r.meets(range) || r.overlaps(range)){
                  throw new IllegalArgumentException("The Airplane selected is not available for the specified dates");
              }
          }
          return true;
    }

}
