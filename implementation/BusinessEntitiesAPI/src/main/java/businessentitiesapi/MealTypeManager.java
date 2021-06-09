package businessentitiesapi;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface MealTypeManager {

    MealType add(MealType mealType);

    void delete(MealType mealType);

    List<MealType> getMeals();

    MealType getMostBookedMeal();

    int getAmountOfPopularMeal(int popMeal);
}
