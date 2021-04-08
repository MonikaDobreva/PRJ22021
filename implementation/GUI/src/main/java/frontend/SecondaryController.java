package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class SecondaryController {

    private final Supplier<SceneManager> sceneManagerSupplier;

    public SecondaryController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }

    @FXML
    Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }




}
