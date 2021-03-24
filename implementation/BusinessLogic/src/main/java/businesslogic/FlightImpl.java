package businesslogic;

import businessentitiesapi.Flight;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightImpl implements Flight {

    private final String name;
    private final ZonedDateTime depTime;
    private final ZonedDateTime arrTime;
    private final String airplane;
    private final String startAirport;
    private final String destAirport;
    // The airplane and airports will be implemented as own classes later!
    // For now they are just Strings. You can delete comment when implemented.

    public FlightImpl(String name,
                      ZonedDateTime depTime,
                      ZonedDateTime arrTime,
                      String airplane,
                      String startAirport,
                      String destAirport) {
        this.name = name;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.airplane = airplane;
        this.startAirport = startAirport;
        this.destAirport = destAirport;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ZonedDateTime getDepartureTime() {
        return depTime;
    }

    @Override
    public ZonedDateTime getArrivalTime() {
        return arrTime;
    }

    @Override
    public String getAirplane() { return airplane; }

    @Override
    public String getStartAirport() { return startAirport; }

    @Override
    public String getDestAirport() { return startAirport; }



    @Override
    public String toString() {
        return name + " leaving at the " + depTime + " and arriving at the " + arrTime;
    }
}
