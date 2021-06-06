package businesslogic;

import persistence.PersistenceAPI;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public interface BusinessLogicImplementationProvider extends BusinessLogicAPI{

    static BusinessLogicAPI getImplementation(PersistenceAPI persistenceAPI){
        return new BusinessLogicAPIImpl(persistenceAPI);
    };
    
//     static BusinessLogicAPI getImplementation(){
//        return new BusinessLogicAPIImpl();
//    };

}
