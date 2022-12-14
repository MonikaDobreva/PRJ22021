package businessentitiesapi;

import java.util.List;

public interface PassengerManager {

    Passenger createPassenger(String passportNumber, int personId);

    Passenger add(Passenger p);

    List<Passenger> getPassengers();
}
