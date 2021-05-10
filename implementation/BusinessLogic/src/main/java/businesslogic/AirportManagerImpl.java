package businesslogic;

import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import persistence.AirplaneStorageService;
import persistence.AirportStorageService;
import persistence.FlightStorageService;

import java.util.List;
import java.util.stream.Collectors;

public class AirportManagerImpl implements AirportManager {

    private AirportStorageService airportStorageService;

    public void setAirportStorageService(AirportStorageService airportStorageService) {
        this.airportStorageService = airportStorageService;
    }

    @Override
    public Airport createAirport(int airportID, String iataCode, String fullName, String country, String city) {
        return new Airport(airportID, iataCode, fullName, country, city);
    }

    @Override
    public void add(Airport a) {
        airportStorageService.add(a);
    }

    @Override
    public List<Airport> getAirports() {
        return airportStorageService.getAll();
    }

    @Override
    public List<Airport> getAirportsWithoutOrigin(String iataCode) {
        return getAirports().stream().filter(a -> !a.getIataCode().equals(iataCode)).collect(Collectors.toList());
    }

    @Override
    public void delete(Airport a) {
        airportStorageService.delete(a);
    }
}
