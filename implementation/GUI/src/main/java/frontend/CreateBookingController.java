package frontend;

import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CreateBookingController {

    private final Supplier<SceneManager> sceneManager;
    private final FlightManager flightManager;
    private Map<String, Flight> flightMap;

    public CreateBookingController(Supplier<SceneManager> sceneManager, FlightManager flightManager) {
        this.sceneManager = sceneManager;
        this.flightManager = flightManager;
    }

    @FXML
    ComboBox<String> selectFlight;

    @FXML
    CheckBox passengerOrNot;

    @FXML
    ComboBox<String> additionalPassenger;

    @FXML
    Button submitButton;

    @FXML
    PassengerInfoController passengerInfoController;

    @FXML
    void onPassengerOrNotBoxClicked() {
        var boxState = passengerOrNot.isSelected();
        passengerInfoController.setPassengerFields(boxState);
    }

    @FXML
    void onFlightSelected() {
        var selectedFlight = selectFlight.getSelectionModel().getSelectedItem();
        var actualFlight = flightMap.get(selectedFlight);
        if (actualFlight == null) {
            return;
        }

        passengerInfoController.setFlight(actualFlight);

        if (passengerOrNot.isSelected()) {
            passengerInfoController.setPassengerFields(true);
        }

    }

    @FXML
    void initialize() {
        var flights = flightManager.getFlights();
        //Create a map that maps the string representation of a flight to an actual flight, example: Flight 1 -> 1
        flightMap = flights.stream().collect(Collectors.toMap(f -> "Flight " + f.getFlightID(), Function.identity()));
        //Convert list of flights to observable list of strings, because setItems method expects observableList
        var flightNames = flightMap.keySet().stream().sorted()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        selectFlight.setItems(flightNames);
    }
}
