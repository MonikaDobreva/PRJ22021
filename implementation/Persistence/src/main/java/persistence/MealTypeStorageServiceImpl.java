package persistence;

import businessentitiesapi.Booking;
import businessentitiesapi.MealType;
import businessentitiesapi.MealTypeManager;
import businessentitiesapi.SeatType;
import genericdao.dao.DAO;
import genericdao.dao.TransactionToken;
import genericdao.pgdao.PGDAOFactory;
import genericdao.pgdao.PGJDBCUtils;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class MealTypeStorageServiceImpl implements MealTypeStorageService{

    private final DAO<MealType, Integer> mealTypeDAO;

    public MealTypeStorageServiceImpl(MealTypeManager mealTypeManager) {
        DataSource ds = PGJDBCUtils.getDataSource("postgres");
        PGDAOFactory daof = new PGDAOFactory(ds);
        mealTypeDAO = daof.createDao(MealType.class);
    }

    @Override
    public MealType add(MealType mt) {

        Optional<MealType> storedMealType = Optional.empty();
        try {
            TransactionToken tok = mealTypeDAO.startTransaction();
            storedMealType = mealTypeDAO.save(mt);
            tok.commit();
            mealTypeDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedMealType.get();
    }

    @Override
    public List<MealType> getAll() {
        try {
            TransactionToken tok = mealTypeDAO.startTransaction();
            Collection<MealType> all = mealTypeDAO.getAll();
            mealTypeDAO.close();
            return new ArrayList<>(all);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<MealType> getMostBookedMeal() {
        List<MealType> bestMeal = null;
        try {
            TransactionToken tok = mealTypeDAO.startTransaction();
            bestMeal = mealTypeDAO.anyQuery(
                    "select id, name " +
                            "from (select m.*, count(m.name) as occurrences" +
                            " from meal_types m " +
                            "join tickets t on m.id = t.meal_id " +
                            "group by m.id " +
                            "order by occurrences desc LIMIT 1) as pmto;");
            tok.commit();
            mealTypeDAO.close();
            return new ArrayList<>(bestMeal);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong with this query");
        }
        return bestMeal;
    }
}
