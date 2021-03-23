package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class SecondaryController {

    @FXML
    Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
        GUIApp.setRoot("flightView");
    }


}
