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
    public List<MealType> getMeals() {
        return new ArrayList<>(daoF.createDao(MealType.class).getAll());
    }

    @Override
    public int getBookedMealsForSpecificFlight(int flightID) {
        try {
            var query = "select mt.* " +
                    "from meal_types mt " +
                    "join tickets t on mt.id = t.meal_id " +
                    "join flight_seats fs on fs.id = t.flight_seat_id " +
                    "join flights f on f.id = fs.flight_id " +
                    "where f.id = (?);";
            return (int) new ArrayList<>(daoF.createDao(MealType.class).anyQuery(query, flightID)).stream()
                    .filter(mt -> !mt.getMealName().equals("None")).count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public MealType getMostBookedMeal() {
        try {
            var query =
                    "select id, name " +
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
    public MealType getLeastBookedMeal() {
        try {
            var query =
                    "select id, name " +
                            "from (select m.*, count(m.name) as occurrences" +
                            " from meal_types m " +
                            "join tickets t on m.id = t.meal_id " +
                            "group by m.id " +
                            "order by occurrences LIMIT 1) as pmto;";
            return new ArrayList<>(daoF.createDao(MealType.class).anyQuery(query)).stream().findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong in the method: \"getMostBookedMeal\" ");
            return null;
        }
    }

    @Override
    public int getAmountOfMeals(int mealID) {
        try {
            var query = "select mt.* " +
                    "from meal_types mt " +
                    "join tickets t on mt.id = t.meal_id " +
                    "where mt.id = (?);";
            return new ArrayList<>(daoF.createDao(MealType.class).anyQuery(query, mealID)).size();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong in the method: \"getAmountOfMeals\" ");
            return 0;
        }
    }
}
