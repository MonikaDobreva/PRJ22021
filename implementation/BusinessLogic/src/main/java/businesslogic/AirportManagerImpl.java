package businesslogic;

import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import genericdao.dao.DAOFactory;
import genericdao.dao.TransactionToken;
import persistence.AirplaneStorageService;
import persistence.AirportStorageService;
import persistence.FlightStorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AirportManagerImpl implements AirportManager {

    private AirportStorageService airportStorageService;
    private DAOFactory daof;

    public void setAirportStorageService(AirportStorageService airportStorageService, DAOFactory daof) {
        this.airportStorageService = airportStorageService;
        this.daof = daof;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        this.daof = pgdFactory;
    }

    @Override
    public Airport createAirport(int airportID, String iataCode, String fullName, String city, String country) {
        return new Airport(airportID, iataCode, fullName, city, country);
    }

    @Override
    public List<Airport> getAirports() {
//        return airportStorageService.getAll();
        try {
            return new ArrayList<>(daof.createDao(Airport.class).getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Airport> getAirportsWithoutOrigin(String iataCode) {
        return getAirports().stream().filter(a -> !a.getIataCode().equals(iataCode)).collect(Collectors.toList());
    }

    @Override
    public Airport getAirport(String airportIataCode) {
        return this.getAirports().stream().filter(a -> a.getIataCode().equals(airportIataCode)).findFirst().get();
    }

}
