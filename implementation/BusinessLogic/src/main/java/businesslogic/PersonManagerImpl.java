package businesslogic;

import businessentitiesapi.Person;
import businessentitiesapi.PersonManager;
import genericdao.dao.DAOFactory;
import persistence.PersonStorageService;

import java.time.LocalDate;
import java.util.List;

public class PersonManagerImpl implements PersonManager {

    private PersonStorageService personStorageService;
    private DAOFactory daoF;

    public void setPersonStorageService(PersonStorageService personStorageService, DAOFactory pgdFactory) {
        this.personStorageService = personStorageService;
        daoF = pgdFactory;
    }

    public void setDaoFactory(DAOFactory pgdFactory) {
        daoF = pgdFactory;
    }

    @Override
    public Person createPerson(
            String firstName,
            String lastName,
            String email,
            String gender,
            LocalDate birthDate
    ) {
        //The 1 is just a placeholder
        return new Person(1, firstName, lastName, email, gender, birthDate);
    }

    @Override
    public Person add(Person p) {
        return daoF.createDao(Person.class).save(p).get();
    }

    @Override
    public List<Person> getPersons() {
        return daoF.createDao(Person.class).getAll();
    }

    @Override
    public Person getPersonBooked(int bookingID) {
        try {
            String query =
                    "select p.personId, p.firstname, p.lastname, p.email, p.gender, p.birthdate " +
                    "from personsview p " +
                    "join bookings b on p.personid = b.person_id " +
                    "where b.id = (?);";
            return daoF.createDao(Person.class).anyQuery(query, bookingID).get(0);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Person getPersonByID(int personID){
        try{
            return daoF.createDao(Person.class).get(personID).get();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
