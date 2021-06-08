package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("airplaneSchedule")
public class AirplaneSchedule implements Serializable {

    @ID
    private String airplaneCode;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public AirplaneSchedule(String airplaneCode, LocalDateTime departureTime, LocalDateTime arrivalTime){
        this.airplaneCode = airplaneCode;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getAirplaneCode() {
        return airplaneCode;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "AirplaneSchedule{" +
                "airplaneCode='" + airplaneCode + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
