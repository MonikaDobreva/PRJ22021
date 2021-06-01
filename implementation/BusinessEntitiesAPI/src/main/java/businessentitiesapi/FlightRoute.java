package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName("FlightRoutesView")
public class FlightRoute implements Serializable {

    @ID
    int flightRouteID;
    String originAirportCode;
    String destinationAirportCode;

    public FlightRoute(int flightRouteID, String originAirportCode, String destinationAirportCode) {
        this.flightRouteID = flightRouteID;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
    }

    public int getFlightRouteID() {
        return flightRouteID;
    }

    public String getOriginAirportCode() {
        return originAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    @Override
    public String toString() {
        return "FlightRoute " + flightRouteID + "from airport: " + originAirportCode + " to airport: " + destinationAirportCode;
    }
}
