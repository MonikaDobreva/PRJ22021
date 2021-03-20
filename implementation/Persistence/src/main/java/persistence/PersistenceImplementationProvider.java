package persistence;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface PersistenceImplementationProvider extends PersistenceAPI{

    static PersistenceAPI getImplementation(){
        return new PersistenceAPIImpl();
    }

}
