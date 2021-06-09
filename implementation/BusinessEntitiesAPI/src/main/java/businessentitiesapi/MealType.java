package businessentitiesapi;

import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

import java.io.Serializable;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

@TableName(value = "meal_types")
public class MealType implements Serializable {

    @ID
    private final int mealId;
    private final String mealName;

    /**
     * @param id        Id of the Meal type
     * @param mealName  Name of the meal type
     */
    public MealType(int id, String mealName) {
        this.mealId = id;
        this.mealName = mealName;
    }


//    public enum MEALS {
//        NONE,
//        STANDARD,
//        VEGAN,
//        VEGETARIAN,
//        MUSLIM,
//        KOSHER,
//        CHILD_MEAL,
//        DIABETIC,
//        GLUTEN_FREE,
//        LACTOSE_FREE
//    }

    public int getId() {
        return mealId;
    }

    public String getMealName() {
        return mealName.toString();
    }

    @Override
    public String toString() {
        return "MealType{" +
                "id=" + mealId +
                ", mealName='" + mealName + '\'' +
                '}';
    }
}
