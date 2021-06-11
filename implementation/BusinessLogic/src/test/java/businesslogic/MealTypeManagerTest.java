package businesslogic;

import businessentitiesapi.MealType;
import genericdao.dao.DAO;
import genericdao.dao.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */


public class MealTypeManagerTest {

    @Mock
    DAOFactory daoF;
    DAO<MealType, Serializable> dao;

    MealTypeManagerImpl mtmi;
    MealType m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;
    List<MealType> popularMeals, singlePopularMeal, meals;

    @BeforeEach
    public void setupMock(){
        mtmi = new MealTypeManagerImpl();
        daoF = mock(DAOFactory.class);
        dao = mock(DAO.class);

        mtmi.setDaoFactory(daoF);
        when(daoF.createDao(MealType.class)).thenReturn(dao);

        m1 = new MealType(1, "Kosher");
        m2 = new MealType(2, "Child Meal");
        m3 = new MealType(3, "Vegan");
        m4 = new MealType(4, "Vegetarian");
        m5 = new MealType(5,"None");
        m6 = new MealType(6, "Diabetic");
        m7 = new MealType(7, "Gluten-free");
        m8 = new MealType(8, "None");
        m9 = new MealType(9, "Vegetarian");
        m10 = new MealType(10, "Vegetarian");

        meals = Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10);

        popularMeals = Arrays.asList(m4, m9, m10);
        singlePopularMeal = Arrays.asList(m4);

    }

    @Test
    public void tGetAll(){
        when(dao.getAll()).thenReturn(meals);
        var tList = mtmi.getMeals();
        verify(dao).getAll();
        assertThat(tList).containsSequence(meals);
    }

    @Test
    public void tGetMostPopularMealType(){
        when(dao.anyQuery(anyString())).thenReturn(singlePopularMeal);
        var popMeal = mtmi.getMostBookedMeal().getMealName();
        verify(daoF).createDao(MealType.class);
        verify(dao).anyQuery(anyString());
        assertThat(popMeal).isEqualTo("Vegetarian");
    }

    @Test
    public void tGetAmountOfPopularMeal(){
        when(dao.anyQuery(anyString(), any())).thenReturn(popularMeals);
        var popMeal = mtmi.getAmountOfMeals(1);
        verify(daoF).createDao(MealType.class);
        verify(dao).anyQuery(anyString(), any());
        assertThat(popMeal).isEqualTo(3);
    }

    @Test
    public void tGetBookedMealsForSpecificFlight(){
        when(dao.anyQuery(anyString(), any())).thenReturn(meals);
        var bookedMeals = mtmi.getBookedMealsForSpecificFlight(1);
        verify(daoF).createDao(MealType.class);
        verify(dao).anyQuery(anyString(), any());
        assertThat(bookedMeals).isEqualTo(8);
    }


}
