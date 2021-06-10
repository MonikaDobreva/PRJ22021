package businesslogic;

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

public class CreateFlightLogic {
    AirplaneManager airplaneManager;
    AirportManager airportManager;
    FlightManager flightManager;
    FlightRouteManager flightRouteManager;
    SeatManager seatManager;
    FlightSeatManager flightSeatManager;
    AirplaneScheduleManager airplaneScheduleManager;
    Map<String, String> values;

    public CreateFlightLogic(AirplaneManager airplaneManager, AirportManager airportManager, FlightManager flightManager,
                             FlightRouteManager flightRouteManager, SeatManager seatManager, FlightSeatManager flightSeatManager,
                             AirplaneScheduleManager airplaneScheduleManager) {
        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;
        this.flightManager = flightManager;
        this.flightRouteManager = flightRouteManager;
        this.seatManager = seatManager;
        this.flightSeatManager = flightSeatManager;
        this.airplaneScheduleManager = airplaneScheduleManager;
    }

    /**
     * Small helper method, which adds an additional 0 to the time if it is only
     * one digit. Otherwise the selected time cannot be properly parsed.
     *
     * @param t time value
     * @return formatted time value
     */
    public String makeTimeValid(String t) {
        return t.length() == 1 ? "0" + t : t;
    }

    public LocalDateTime makeDateTime(String hour, String min, String date) {
        return LocalDateTime.parse(
                makeTimeValid(hour)
                        + ":"
                        + makeTimeValid(min)
                        + " "
                        + date,
                DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
    }

    public void setData(Map<String, String> dataCollected){
        this.values = dataCollected;
    }

//    public void dataValidation(Map<String, String> values)
//            throws DateTimeParseException, NoSuchElementException, NullPointerException{
    public void dataValidation(Map<String, String> values) {
        Integer.parseInt(values.get("flightID"));
        makeDateTime(values.get("dTHour"), values.get("dTMin"), values.get("dTDate"));
        makeDateTime(values.get("aTHour"), values.get("aTMin"), values.get("aTDate"));
        new BigDecimal(values.get("price"));

    }

    public Airplane obtainAirplane() {
        return airplaneManager.getAirplane(values.get("airplaneInfo").split(" ")[0]);
    }

//    public void storeFlight() throws IllegalArgumentException{
    public void storeFlight() throws FlightStorageException {
        Airplane airplane = this.obtainAirplane();

        LocalDateTime depTime = makeDateTime(values.get("dTHour"), values.get("dTMin"), values.get("dTDate"));
        LocalDateTime arrTime = makeDateTime(values.get("aTHour"), values.get("aTMin"), values.get("aTDate"));

        airplaneScheduleManager.checkAvailability(airplane.getAirplaneCode(), depTime, arrTime);

        Flight f = flightManager.createFlight(
                Integer.parseInt(values.get("flightID")),
                values.get("originAirport"),
                values.get("destinationAirport"),
                depTime,
                arrTime,
                airplane.getAirplaneCode(),
                new BigDecimal(values.get("price"))
        );

        flightRouteManager.checkExistence(values.get("originAirport"), values.get("destinationAirport"));

        flightManager.add(f);

        List<Integer> seatsId = seatManager.getSeatIdsOfAirplane(airplane);
        for (int seatId : seatsId){
            flightSeatManager.add(flightSeatManager.createFlightSeat(seatId, f.getFlightID(), true));
        }


    }

    public List<Flight> obtainFlights() {
        return flightManager.getFlights();
    }

    public List<String> listOriginAirport(){
        return airportManager.getAirports().stream()
                .map(Airport::getIataCode)
                .collect(Collectors.toList());
    }

    public List<String> listDestinationAirport(String originAirport){
        return airportManager.getAirportsWithoutOrigin(originAirport).stream()
                .map(Airport::getIataCode)
                .collect(Collectors.toList());
    }

    public List<String> listAirplanes(){
        return airplaneManager.getAirplanes().stream()
                .map(a -> a.getAirplaneCode() + " (" + a.getModel() + ")")
                .collect(Collectors.toList());
    }

    public String getNextID() {
        return String.valueOf(flightManager.getLastID() + 1);
    }

    public void clearData(){
        this.values = new HashMap<>();
    }
}
