package businesslogic;

import businessentitiesapi.Flight;

import java.time.LocalDate;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightImpl implements Flight {

    private final String name;
    private final LocalDate depTime;
    private final LocalDate arrTime;

    public FlightImpl(String name, LocalDate depTime, LocalDate arrTime) {
        this.name = name;
        this.depTime = depTime;
        this.arrTime = arrTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getDepartureTime() {
        return depTime;
    }

    @Override
    public LocalDate getArrivalTime() {
        return arrTime;
    }

    @Override
    public String toString(){
        return "Flight: " + name + " leaving at the " + depTime + " and arriving at the " + arrTime;
    }
}
