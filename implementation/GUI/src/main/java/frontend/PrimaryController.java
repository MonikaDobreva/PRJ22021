package frontend;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class PrimaryController {

    final Switcher switcher;
    
    @FXML
    CheckBox cb1;

    public PrimaryController( Switcher switcher ) {
        this.switcher = switcher;
    }

    @FXML
    void switchToSecondary() throws IOException {
        switcher.setRoot( "secondary" );
    }
}
