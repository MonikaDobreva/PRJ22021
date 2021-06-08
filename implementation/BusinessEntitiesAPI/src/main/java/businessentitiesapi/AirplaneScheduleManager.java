package businessentitiesapi;

import nl.fontys.sebivenlo.ranges.LocalDateTimeRange;

import java.time.LocalDateTime;
import java.util.List;

public interface AirplaneScheduleManager {

    List<AirplaneSchedule> getAirplaneSchedule(String airplaneCode);

    List<LocalDateTimeRange> airplaneScheduleRange(String airplaneCode);

    boolean checkAvailability(String airplaneCode, LocalDateTime departure, LocalDateTime arrival) throws IllegalArgumentException;
}
