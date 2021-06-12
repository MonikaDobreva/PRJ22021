package businessentitiesapi;

import businessentitiesapi.*;
import businessentitiesapi.exceptions.FlightStorageException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public interface CreateFlightLogic {


    void setData(Map<String, String> dataCollected);

    void clearData();

    void dataValidation(Map<String, String> values);

    Airplane obtainAirplane();

    void storeFlight() throws FlightStorageException;

    List<Flight> obtainFlights();

    List<String> listOriginAirport();

    List<String> listDestinationAirport(String originAirport);

    List<String> listAirplanes();

    String getNextID();

    String getAirplaneInfo(String airplane);

    String getAirportInfo(String airport);
}

