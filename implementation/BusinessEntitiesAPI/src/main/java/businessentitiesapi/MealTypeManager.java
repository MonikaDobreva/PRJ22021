package businessentitiesapi;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface MealTypeManager extends Manager<MealType> {


    List<MealType> getMostBookedMeal();
}
