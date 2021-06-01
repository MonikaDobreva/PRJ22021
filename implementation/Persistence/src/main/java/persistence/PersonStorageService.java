package persistence;

import businessentitiesapi.Person;

import java.util.List;

public interface PersonStorageService {

    Person add(Person p);
    List<Person> getAll();
}
