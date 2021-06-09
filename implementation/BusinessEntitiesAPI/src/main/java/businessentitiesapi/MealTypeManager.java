package businessentitiesapi;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface MealTypeManager extends Manager<MealType> {

    MealType getMostBookedMeal();

    int getAmountOfPopularMeal(int popMeal);
}
