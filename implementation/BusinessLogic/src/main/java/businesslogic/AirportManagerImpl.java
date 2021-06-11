package businesslogic;

import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import genericdao.dao.DAOFactory;
import persistence.AirportStorageService;

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
            return daof.createDao(Airport.class).getAll();
    }

    @Override
    public List<Airport> getAirportsWithoutOrigin(String iataCode) {
        return getAirports().stream().filter(a -> !a.getIataCode().equals(iataCode)).collect(Collectors.toList());
    }

    @Override
    public Airport getAirport(String airportIataCode) {
        return this.getAirports().stream().filter(a -> a.getIataCode().equals(airportIataCode)).findFirst().get();
    }

    @Override
    public Airport mostPopularAirport() {
        String query =
                "select a.id, a.iata_code, a.full_name, a.city, a.country " +
                "from airports a " +
                "join flight_routes fr on a.id = fr.destination_airport_id " +
                "join flights f on fr.id = f.flight_route_id " +
                "join flight_seats fs on f.id = fs.flight_id " +
                "join tickets t on fs.id = t.flight_seat_id " +
                "join bookings b on b.id = t.booking_id " +
                "group by a.id " +
                "order by count(a.iata_code) desc " +
                "LIMIT 1;";
        return daof.createDao(Airport.class).anyQuery(query).get(0);
    }

}
