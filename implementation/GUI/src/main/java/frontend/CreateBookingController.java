package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.util.function.Supplier;

public class CreateBookingController {

    public CreateBookingController(Supplier<SceneManager> sceneManager) {
    }

    @FXML
    ComboBox<String> selectFlight;

    @FXML
    CheckBox passengerOrNot;

    @FXML
    ComboBox<String> additionalPassenger;

    @FXML
    Button submitButton;


}
