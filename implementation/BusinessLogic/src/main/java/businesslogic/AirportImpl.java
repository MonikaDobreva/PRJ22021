package businesslogic;

import java.util.Locale;

public class AirportImpl implements businessentitiesapi.Airport {

    private final String iataCode; // IATA = International Air Transport Association
    private final String fullName;
    private final String country;
    private final String city; //Make Locale later?

    public AirportImpl(String iataCode, String fullName, String country, String city) {
        this.iataCode = iataCode;
        this.fullName = fullName;
        this.country = country;
        this.city = city;
    }

    @Override
    public String getIataCode() {
        return iataCode;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return fullName + " (" + iataCode + ") " + city + ", " + country;
    }
}
