package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PassengerInfoController {

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public ComboBox<String> gender;

    @FXML
    public TextField email;

    @FXML
    public DatePicker birthday;

    @FXML
    public TextField passportNumber;

    @FXML
    public ComboBox<String> seatNumber;

    @FXML
    public ComboBox<String> cabinLuggage;

    @FXML
    public ComboBox<String> handLuggage;

    @FXML
    public ComboBox<String> meal;

    void enablePassengerFields() {
        passportNumber.setDisable(false);
        seatNumber.setDisable(false);
        cabinLuggage.setDisable(false);
        handLuggage.setDisable(false);
        meal.setDisable(false);
    }
}
