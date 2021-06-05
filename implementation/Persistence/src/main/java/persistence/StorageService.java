package persistence;

import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface StorageService<T> {

    T add(T toAdd);

    List<T> getAll();

}
