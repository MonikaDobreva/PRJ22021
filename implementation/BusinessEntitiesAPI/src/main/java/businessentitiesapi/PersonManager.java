package businessentitiesapi;

import java.time.LocalDate;
import java.util.List;

public interface PersonManager {

    Person createPerson(String firstName, String lastName, String email, String gender, LocalDate birthDate);

    Person add(Person p);

    List<Person> getPersons();

    Person getPersonBooked(int value);

    Person getPersonByID(int personID);
}
