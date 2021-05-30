package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Locale;
import java.util.function.Supplier;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class welcomeController {

    private final Supplier<SceneManager> sceneManagerSupplier;

    @FXML
    private MenuButton languageMenu;

    @FXML
    private ComboBox<String> userChoice;

    @FXML
    Button startBtn;

    public welcomeController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }
    @FXML
    public void goToStart() throws IOException {
        if (userChoice.getValue().toLowerCase().contains("officer")){
            sceneManagerSupplier.get().changeScene("salesOfficerOptions");
        } else {
            sceneManagerSupplier.get().changeScene("viewFlights");
        }
    }

    @FXML
    public void userRoles(){
        String[] userRoles = {"Sales Employee", "Sales Officer", "Sales Manager"};
        userChoice.setItems(FXCollections.observableArrayList(userRoles));
    }
    
    @FXML
    private void changeLanguageGerman(ActionEvent event) {
        Locale.setDefault(Locale.GERMANY);
    }

    @FXML
    private void changeLanguageEnglish(ActionEvent event) {
        //this is actually referring to something that is actually not there.
        //it will then drop to default
        Locale.setDefault(Locale.ENGLISH);
    }

    @FXML
    private void changeLanguageDutch(ActionEvent event) {
        Locale.setDefault(new Locale("nl", "NL"));
    }

}
