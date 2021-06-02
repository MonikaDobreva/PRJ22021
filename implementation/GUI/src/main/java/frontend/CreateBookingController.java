package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.util.function.Supplier;

public class CreateBookingController {

    private final Supplier<SceneManager> sceneManager;

    public CreateBookingController(Supplier<SceneManager> sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    ComboBox<String> selectFlight;

    @FXML
    CheckBox passengerOrNot;

    @FXML
    ComboBox<String> additionalPassenger;

    @FXML
    Button submitButton;

    @FXML
    PassengerInfoController passengerInfoController;


}
