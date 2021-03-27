package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class welcomeController {

    @FXML
    Button startBtn;

    @FXML
    public void goToStart() throws IOException {
        GUIApp.setRoot("flightView");
    }

}
