package businesslogic;

import businessentitiesapi.Booking;
import businessentitiesapi.Passenger;
import businessentitiesapi.PassengerManager;
import persistence.PassengerStorageService;

import java.util.List;

public class PassengerManagerImpl implements PassengerManager {

    private PassengerStorageService passengerStorageService;

    public void setPassengerStorageService(PassengerStorageService passengerStorageService) {
        this.passengerStorageService = passengerStorageService;
    }


    @Override
    public Passenger createPassenger(String passportNumber, String personId) {
        //The 0 is just a placeholder
        return new Passenger(0, passportNumber, personId);
    }

    @Override
    public Passenger add(Passenger p) {
        return passengerStorageService.add(p);
    }

    @Override
    public List<Passenger> getPassengers() {
        return passengerStorageService.getAll();
    }
}
