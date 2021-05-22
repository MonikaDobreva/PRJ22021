package frontend;

import java.util.function.Supplier;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class managementDashboardController {

    private final Supplier<SceneManager> sceneManagerSupplier;

    public managementDashboardController(Supplier<SceneManager> sceneManagerSupplier){
        this.sceneManagerSupplier = sceneManagerSupplier;
    }



}
