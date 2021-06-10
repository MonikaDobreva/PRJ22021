package businesslogic;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import businessentitiesapi.FlightRouteManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rachel
 */
public class EditDetailsLogic {

    FlightManager flightManager;
    FlightRouteManager flightRouteManager;

    public EditDetailsLogic(FlightManager fm, FlightRouteManager fr) {
        this.flightManager = fm;
        this.flightRouteManager = fr;
    }

    /**
     * This method cares about the passed data from the controller and processes
     * it.
     */
    public Map<String, String> passData(Map<String, String> savedValues, Flight flightE) {
        //this stores the diffrent results and commands
        //same,-no value-if no changes occured
        //flightError,flight constructor catch
        //updateError,-no value-is if update returns false
        //worked,-no value-
        Map<String, String> returnData = new HashMap<>();

        try {
            Flight f = flightManager.createFlight(
                    flightE.getFlightID(), //should be same flightID for update
                    savedValues.getOrDefault("cVOrigin", flightE.getOriginAirport()),
                    savedValues.getOrDefault("cVDestination", flightE.getDestinationAirport()),
                    this.makeDateTime(
                            savedValues.getOrDefault("cVDepHour",
                                    String.valueOf(flightE.getDepartureTime().getHour())),
                            savedValues.getOrDefault("cVDepMin",
                                    String.valueOf(flightE.getDepartureTime().getMinute())),
                            savedValues.getOrDefault("cVDepDate",
                                    flightE.getDepartureTime().toLocalDate().toString())),
                    this.makeDateTime(
                            savedValues.getOrDefault("cVArrHour",
                                    String.valueOf(flightE.getArrivalTime().getHour())),
                            savedValues.getOrDefault("cVArrMin",
                                    String.valueOf(flightE.getArrivalTime().getMinute())),
                            savedValues.getOrDefault("cVArrDate",
                                    flightE.getArrivalTime().toLocalDate().toString())),
                    savedValues.getOrDefault("cVAirplane", flightE.getAirplane()),
                    new BigDecimal(savedValues.getOrDefault("cVPrice", flightE.getBasePrice().toPlainString())));
            
             if (f.equals(flightE)) {
                returnData.put("same", "");
                return returnData;
            }

            flightRouteManager.checkExistence(savedValues.getOrDefault("cVOrigin", flightE.getOriginAirport()),
                    savedValues.getOrDefault("cVDestination", flightE.getDestinationAirport()));

            boolean update = flightManager.update(f);

            if (update == true) {
                returnData.put("worked", "");
            } else {
                returnData.put("updateError", "");
            }

        } catch (Exception e) {
            returnData.put("flightError", e.getMessage());
        }

        return returnData;
    }

    /**
     * This makes the a LocalDateTime from hour minute and a date.
     *
     * @param hour
     * @param min
     * @param date
     * @return
     */
    LocalDateTime makeDateTime(String hour, String min, String date) {
        return LocalDateTime.parse(
                makeTimeValid(hour)
                + ":"
                + makeTimeValid(min)
                + " "
                + date,
                DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
    }

    /**
     * Small helper method, which adds an additional 0 to the time if it is only
     * one digit. Otherwise the selected time cannot be properly parsed.
     *
     * @param t time value
     * @return formatted time value
     */
    private String makeTimeValid(String t) {
        return t.length() == 1 ? "0" + t : t;
    }
}
