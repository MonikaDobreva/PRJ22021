package persistence;

import businessentitiesapi.MealType;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface MealTypeStorageService {


    MealType add(MealType mt);

    List<MealType> getAll();

    List<MealType> getMostBookedMeal();
}
