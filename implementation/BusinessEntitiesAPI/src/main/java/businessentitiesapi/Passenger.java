package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

@TableName(value = "passengersView")
public class Passenger implements Serializable {
    @ID
    private final int passengerId;

    private final String passportNumber, personId;

    public Passenger(int passengerId, String passportNumber, String personId) {
        this.passengerId = passengerId;
        this.passportNumber = passportNumber;
        this.personId = personId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPersonId() {
        return personId;
    }
}
