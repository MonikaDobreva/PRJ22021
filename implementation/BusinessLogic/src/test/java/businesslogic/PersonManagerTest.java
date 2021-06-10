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
    }

    @Test
    public void tGetPersonBooked(){
        when(dao.anyQuery(anyString(), any())).thenReturn(persons);
        var booker = pmi.getPersonBooked(1).getFirstName();
        verify(daoF).createDao(Person.class);
        verify(dao).anyQuery(anyString(), any());
        assertThat(booker).isEqualTo("Joel");
    }

}
