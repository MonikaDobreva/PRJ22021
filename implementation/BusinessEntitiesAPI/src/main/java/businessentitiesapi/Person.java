package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.time.LocalDate;

@TableName(value = "personsView")
public class Person {
    @ID
    private final int personId;

    private final String firstName, lastName, email, gender;
    private final LocalDate birhDate;

    public Person(int personId, String firstName, String lastName, String email, String gender, LocalDate birhDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birhDate = birhDate;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirhDate() {
        return birhDate;
    }
}
