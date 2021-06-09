package businesslogic;

import businessentitiesapi.MealType;
import businessentitiesapi.MealTypeManager;
import genericdao.dao.DAOFactory;
import persistence.MealTypeStorageService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class MealTypeManagerImpl implements MealTypeManager {

    MealTypeStorageService mealTypeStorageService;
    private DAOFactory daoF;

    public void setMealTypeStorageService(MealTypeStorageService mealTypeStorageService, DAOFactory daoFactory) {
        this.mealTypeStorageService = mealTypeStorageService;
        daoF = daoFactory;
    }

    public void setDaoFactory(DAOFactory daoFactory) {
        daoF = daoFactory;
    }

    @Override
    public MealType add(MealType mealType) {
        return null;
    }

    @Override
    public void delete(MealType mealType) {
    }

    @Override
    public List<MealType> get() {
        try {
            return new ArrayList<>(daoF.createDao(MealType.class).getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<MealType> getMeals() {
//        try {
//            return new ArrayList<>(daoF.createDao(MealType.class).getAll());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public MealType getMostBookedMeal() {
        try {
            var query = "select id, name " +
                    "from (select m.*, count(m.name) as occurrences" +
                    " from meal_types m " +
                    "join tickets t on m.id = t.meal_id " +
                    "group by m.id " +
                    "order by occurrences desc LIMIT 1) as pmto;";
            return new ArrayList<>(daoF.createDao(MealType.class).anyQuery(query)).stream().findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong in the method: \"getMostBookedMeal\" ");
            return null;
        }
    }

    @Override
    public int getAmountOfPopularMeal(int popMeal) {
        try {
            var query = "select mt.* " +
                    "from meal_types mt " +
                    "join tickets t on mt.id = t.meal_id " +
                    "where mt.id = (?);";
            return new ArrayList<>(daoF.createDao(MealType.class).anyQuery(query, popMeal)).size();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong in the method: \"getAmountOfPopularMeal\" ");
            return 0;
        }
    }
}
