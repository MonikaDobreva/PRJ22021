package frontend;

import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.function.Supplier;

public class SalesOfficerController {

    private final Supplier<SceneManager> sceneManagerSupplier;

    @FXML
    Button createFlightBtn, editFlightBtn, viewFlightsBtn, viewFlightRoutesBtn, logoutBtn;

    public SalesOfficerController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }

    @FXML
    public void goToCreateFlight(){
        sceneManagerSupplier.get().changeScene("createFlight");
    }

    @FXML
    public void goToEditFlight(){
       Consumer<editFlightController> cons = (editFlightController c) -> c.initWindow();
        sceneManagerSupplier.get().changeScene("editFlights", cons);
    }

    @FXML
    public void goToViewFlights(){
        sceneManagerSupplier.get().changeScene("viewFlights");
    }

    @FXML
    public void goToViewFlightRoutes(){
        sceneManagerSupplier.get().changeScene("salesOfficerOptions");
    }

    @FXML
    public void logout(){
        sceneManagerSupplier.get().changeScene("welcome");
    }
}
