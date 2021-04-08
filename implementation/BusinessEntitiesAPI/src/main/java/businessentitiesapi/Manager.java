package businessentitiesapi;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface Manager<T> {

    T add(T t);

    void delete(T t);

    //List<T> get();

}
