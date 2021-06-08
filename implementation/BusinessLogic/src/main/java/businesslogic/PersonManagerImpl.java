package businesslogic;

import businessentitiesapi.Booking;
import businessentitiesapi.Person;
import businessentitiesapi.PersonManager;
import persistence.PersonStorageService;

import java.time.LocalDate;
import java.util.List;

public class PersonManagerImpl implements PersonManager {

    private PersonStorageService personStorageService;

    public void setPersonStorageService(PersonStorageService personStorageService) {
        this.personStorageService = personStorageService;
    }

    @Override
    public Person createPerson(String firstName, String lastName, String email, String gender, LocalDate birthDate) {
        //The 0 is just a placeholder
        return new Person(0, firstName, lastName, email, gender, birthDate);
    }

    @Override
    public Person add(Person p) {
        return personStorageService.add(p);
    }

    @Override
    public List<Person> getPersons() {
        return personStorageService.getAll();
    }
}
