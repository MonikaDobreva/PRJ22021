package assembler;

import businesslogic.BusinessLogicAPI;
import businesslogic.BusinessLogicImplementationProvider;
//import frontend.GUIApp;
import frontend.GUIApp;
import persistence.PersistenceAPI;
import persistence.PersistenceImplementationProvider;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class Main {

    public static void main(String[] args) {

        PersistenceAPI persistenceAPI = PersistenceImplementationProvider.getImplementation();
        var ds = genericdao.pgdao.PGJDBCUtils.getDataSource("postgres");
        BusinessLogicAPI businessLogicAPI = 
                BusinessLogicImplementationProvider.getImplementation(persistenceAPI,new genericdao.pgdao.PGDAOFactory(ds));

//        PersistenceAPI persistenceAPI = PersistenceImplementationProvider.getImplementation();
//        BusinessLogicAPI businessLogicAPI = BusinessLogicImplementationProvider.getImplementation();
        
        GUIApp gui = new GUIApp(businessLogicAPI).start( );

    }

}
