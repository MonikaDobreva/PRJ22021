package frontend;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Rachel
 */
public class DeleteFlightController {

    @FXML
    private Button DeleteButton, cancelDeleteButton;
    @FXML
    private Label warningText, flightLabel;

    private Supplier<SceneManager> sceneManagerSupplier;
    private final FlightManager flightManager;
    private Flight toDeleteFlight;

    public DeleteFlightController(Supplier<SceneManager> sceneManagerSupplier, FlightManager flightManager) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.flightManager = flightManager;
    }
    
    public void setDeleteFlight(Flight f){
        toDeleteFlight=f;
        flightLabel.setText(f.toString());
    }
    

    @FXML
    private void deleteFlight(ActionEvent event) {
        if ( flightManager.delete(toDeleteFlight)) {
        Consumer<editFlightController> cons = (editFlightController c) -> c.initWindow();
        sceneManagerSupplier.get().changeScene("editFlights", cons); 
        }else{
            flightLabel.setText(ResourceBundle.getBundle("frontend.editAisStrings", Locale.getDefault()).getString("problem"));
        }
        
    }

    @FXML
    private void cancelDelete(ActionEvent event) {
        Consumer<editDetailsFlightController> cont = (editDetailsFlightController c) -> c.setFlight(toDeleteFlight);
        sceneManagerSupplier.get().changeScene("editDetailsFlights", cont);
    }

}
