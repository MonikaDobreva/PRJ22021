package businesslogic;

import businessentitiesapi.Person;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class PersonManagerTest {

    @Mock
    DAOFactory daoF;
    DAO<Person, Serializable> dao;

    PersonManagerImpl pmi;
    List<Person> persons;

    Person p1;

    @BeforeEach
    public void setupTest(){
        pmi = new PersonManagerImpl();
        daoF = mock(DAOFactory.class);
        dao = mock(DAO.class);
        pmi.setDaoFactory(daoF);

        when(daoF.createDao(Person.class)).thenReturn(dao);

        p1 = new Person(1, "Joel", "Sebastian", "joelseb@gmail.com", "M", LocalDate.parse("1800-12-12"));
        persons = Arrays.asList(p1);

        pmi.setPersonStorageService(null, daoF);
    }

    @Test
    public void tCreatePerson(){
        Person p = pmi.createPerson("Joel", "Sebastian", "joelseb@gmail.com", "M", LocalDate.parse("1800-12-12"));
        assertThat(p).hasToString(p1.toString());
    }

    @Test
    public void tGetPersonBooked(){
        when(dao.anyQuery(anyString(), any())).thenReturn(persons);
        var booker = pmi.getPersonBooked(1).getFirstName();
        verify(daoF).createDao(Person.class);
        verify(dao).anyQuery(anyString(), any());
        assertThat(booker).isEqualTo("Joel");
    }

    @Test
    public void tBrokenPersonBooked(){
        when(dao.anyQuery(anyString(), any())).thenThrow(RuntimeException.class);
        assertThat(pmi.getPersonBooked(1)).isNull();
        verify(daoF).createDao(Person.class);
        verify(dao).anyQuery(anyString(), any());
    }

    @Test
    public void tBrokenGetPersonBooked(){
        when(dao.anyQuery(anyString(), any())).thenThrow(RuntimeException.class);
        assertThat(pmi.getPersonBooked(1)).isNull();
        verify(daoF).createDao(Person.class);
        verify(dao).anyQuery(anyString(), any());
    }

    @Test
    public void tAdd(){
        when(dao.save(p1)).thenReturn(Optional.of(p1));
        pmi.add(p1);
        verify(daoF).createDao(Person.class);
        verify(dao).save(p1);
    }

    @Test
    public void tGetAll(){
        when(dao.getAll()).thenReturn(persons);
        assertThat(pmi.getPersons()).containsSequence(persons);
        verify(daoF).createDao(Person.class);
        verify(dao).getAll();
    }

    @Test
    public void tGetPersonById(){
        when(dao.get(any())).thenReturn(Optional.of(p1));
        var p = pmi.getPersonByID(1);
        assertThat(p).hasToString(p1.toString());
        verify(daoF).createDao(Person.class);
        verify(dao).get(any());
    }

    @Test
    public void tBrokenGetPersonById(){
        when(dao.get(any())).thenThrow(RuntimeException.class);
        assertThat(pmi.getPersonByID(1)).isNull();
        verify(daoF).createDao(Person.class);
        verify(dao).get(any());
    }

}
