package assembler;

import businesslogic.BusinessLogicAPI;
import businesslogic.BusinessLogicImplementationProvider;
//import frontend.GUIApp;
import persistence.PersistenceAPI;
import persistence.PersistenceImplementationProvider;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class Main {

    public static void main(String[] args) {

        PersistenceAPI persistenceAPI = PersistenceImplementationProvider.getImplementation();
        BusinessLogicAPI businessLogicAPI = BusinessLogicImplementationProvider.getImplementation(persistenceAPI);

//        new GUIApp(businessLogicAPI).show();
        
        var list = businessLogicAPI.getFlightManager().getFlights();
        
        list.stream().forEach(t -> System.out.println(t.toString()));

    }

}
