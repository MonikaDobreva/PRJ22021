package frontend;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    final Switcher switcher;

    public SecondaryController( Switcher switcher ) {
        this.switcher = switcher;
    }
    

    @FXML
    private void switchToPrimary() throws IOException {
        switcher.setRoot("primary");
    }
}