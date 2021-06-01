package persistence;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightRoute;

import java.util.List;

public interface FlightRouteStorageService {

    FlightRoute add(FlightRoute fr);
    List<FlightRoute> getAll();

}
