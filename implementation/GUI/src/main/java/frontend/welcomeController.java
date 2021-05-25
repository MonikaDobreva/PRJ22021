package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Locale;
import java.util.function.Supplier;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class welcomeController {

    private final Supplier<SceneManager> sceneManagerSupplier;
    @FXML
    private MenuButton languageMenu;

    public welcomeController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }

    @FXML
    Button startBtn;

    @FXML
    public void goToStart() throws IOException {
        sceneManagerSupplier.get().changeScene("viewFlights");
    }
    
    @FXML
    private void changeLanguageGerman(ActionEvent event) {
        Locale.setDefault(Locale.GERMANY);
    }

    @FXML
    private void changeLanguageEnglish(ActionEvent event) {
        Locale.setDefault(Locale.ENGLISH);
    }

    @FXML
    private void changeLanguageDutch(ActionEvent event) {
        Locale.setDefault(new Locale("nl", "NL"));
    }

}
