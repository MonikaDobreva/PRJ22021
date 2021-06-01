package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

@TableName(value = "passengersView")
public class Passenger {
    @ID
    private final int passengerId;

    private final String passportNumber;
    private final int personId;

    public Passenger(int passengerId, String passportNumber, int personId) {
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

    public int getPersonId() {
        return personId;
    }
}
