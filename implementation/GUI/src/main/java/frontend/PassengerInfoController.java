package frontend;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightSeatManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PassengerInfoController {

    private Flight flight;
    private final FlightSeatManager flightSeatManager;

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
    public ComboBox<String> seatType;

    @FXML
    public ComboBox<String> seatNumber;

    @FXML
    public ComboBox<String> cabinLuggage;

    @FXML
    public ComboBox<String> handLuggage;

    @FXML
    public ComboBox<String> meal;

    public PassengerInfoController(FlightSeatManager flightSeatManager) {
        this.flightSeatManager = flightSeatManager;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    void setPassengerFields(boolean isEnabled) {
        var actualState = !isEnabled;
        if (flight == null && isEnabled) {
            return;
        }
        System.out.println("hiiiizu");
        passportNumber.setDisable(actualState);
        seatType.setDisable(actualState);
        cabinLuggage.setDisable(actualState);
        handLuggage.setDisable(actualState);
        meal.setDisable(actualState);
    }

    @FXML
    void initialize() {
        System.out.println("hio");
        seatType.setOnAction(this::onSeatTypeSelected);
    }

    @FXML
    public void onSeatTypeSelected(ActionEvent actionEvent) {
        var selectedType = seatType.getSelectionModel().getSelectedItem();
        if ("select".equals(selectedType)) {
            return;
        }

        seatNumber.setDisable(false);
        var seats = flightSeatManager.getAvailableFlightSeats(flight, selectedType);
        System.out.println(seats);
    }
}
