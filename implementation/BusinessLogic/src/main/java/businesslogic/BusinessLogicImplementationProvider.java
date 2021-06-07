package businesslogic;

import genericdao.dao.DAOFactory;
import persistence.PersistenceAPI;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface BusinessLogicImplementationProvider extends BusinessLogicAPI{

    static BusinessLogicAPI getImplementation(PersistenceAPI persistenceAPI,DAOFactory pgdFactory){
        return new BusinessLogicAPIImpl(persistenceAPI,pgdFactory);
    };

}
