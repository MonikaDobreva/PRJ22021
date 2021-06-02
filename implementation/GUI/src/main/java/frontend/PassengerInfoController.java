package frontend;

import businessentitiesapi.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PassengerInfoController {

    private Flight flight;

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

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    void setPassengerFields(boolean isEnabled) {
        var actualState = !isEnabled;
        if (flight == null && isEnabled) {
            return;
        }

        passportNumber.setDisable(actualState);
        seatNumber.setDisable(actualState);
        cabinLuggage.setDisable(actualState);
        handLuggage.setDisable(actualState);
        meal.setDisable(actualState);
    }
}
