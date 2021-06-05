package businesslogic;

import businessentitiesapi.MealType;
import businessentitiesapi.MealTypeManager;
import persistence.MealTypeStorageService;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class MealTypeManagerImpl implements MealTypeManager {

    MealTypeStorageService mealTypeStorageService;

    public void setMealTypeStorageService(MealTypeStorageService mealTypeStorageService) {
        this.mealTypeStorageService = mealTypeStorageService;
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
        return mealTypeStorageService.getAll();
    }

    @Override
    public List<MealType> getMostBookedMeal() {
        return mealTypeStorageService.getMostBookedMeal();
    }
}
