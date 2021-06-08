package persistence;

import businessentitiesapi.Person;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PersonStorageServiceImpl implements PersonStorageService {

    private final DAO<Person, Integer> personDao;

    public PersonStorageServiceImpl() {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        DAO<Person, Integer> x = daof.createDao(Person.class);
        personDao = x;
    }

    @Override
    public List<Person> getAll() {
        try {
            Collection<Person> all = personDao.getAll();
            personDao.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Person add(Person f) {
       return personDao.save(f).get();
    }

}

