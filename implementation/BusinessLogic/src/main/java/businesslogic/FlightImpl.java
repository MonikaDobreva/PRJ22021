package businesslogic;

import businessentitiesapi.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightImpl implements Flight {

    private final String name;
    private final LocalDateTime depTime;
    private final LocalDateTime arrTime;
    private final String airplane;
    private final String startAirport;
    private final String destAirport;
    // The airplane and airports will be implemented as own classes later!
    // For now they are just Strings. You can delete comment when implemented.

    public FlightImpl(
            String name,
            LocalDateTime depTime,
            LocalDateTime arrTime,
            String airplane,
            String startAirport,
            String destAirport
    ) {
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
    public LocalDateTime getDepartureTime() {
        return depTime;
    }

    @Override
    public LocalDateTime getArrivalTime() {
        return arrTime;
    }

    @Override
    public String getAirplane() { return airplane; }

    @Override
    public String getStartAirport() { return startAirport; }

    @Override
    public String getDestAirport() { return destAirport; }

    // Airplane ... on flight ...
    // departing from ... on DAY.MONTH at HH:mm
    // arriving at ... on DAY.MONTH at HH:mm

    @Override
    public String toString() {
        return "Airplane " + airplane + " on flight " + name + "\n"
                + "Departing from " + startAirport + " on " + depTime.getDayOfMonth() + "." + depTime.getMonth() + " at " + depTime.getHour() + ":" + depTime.getMinute() + "\n"
                + "Arriving to " + destAirport + " on " + arrTime.getDayOfMonth() + "." + arrTime.getMonth() + " at " + arrTime.getHour() + ":" + arrTime.getMinute() + "\n";
    }
}
