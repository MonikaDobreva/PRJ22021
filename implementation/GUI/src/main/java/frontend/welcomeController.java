package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class welcomeController {

    private final Supplier<SceneManager> sceneManagerSupplier;

    public welcomeController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }

    @FXML
    Button startBtn;

    @FXML
    public void goToStart() throws IOException {
        sceneManagerSupplier.get().changeScene("viewFlights");
    }

}
