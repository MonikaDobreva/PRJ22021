package frontend;

import businessentitiesapi.*;
import businesslogic.bulkvalidator.BulkException;
import businesslogic.validators.PassengerInfoValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.homberghp.fxuiscraper.FXUIScraper;

import java.util.stream.Collectors;

public class PassengerInfoController {

    @FXML
    public GridPane passengerInfo;

    private Flight flight;
    private final FlightSeatManager flightSeatManager;
    private final SeatManager seatManager;

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
    public ComboBox<Seat> seatNumber;

    @FXML
    public ComboBox<String> cabinLuggage;

    @FXML
    public ComboBox<String> handLuggage;

    @FXML
    public ComboBox<String> meal;

    //Look for all things inside the root pane
    FXUIScraper scraper = () -> passengerInfo;
    PassengerInfoValidator validator = new PassengerInfoValidator();

    public PassengerInfoController(FlightSeatManager flightSeatManager, SeatManager seatManager) {
        this.flightSeatManager = flightSeatManager;
        this.seatManager = seatManager;
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

    void validate() {
        var userInput = scraper.getKeyValues(x -> true);
        System.out.println();
        try {
            validator.validateInput(userInput);
        } catch (BulkException e) {
            var causes = e.getCauses();
            causes.values().forEach(s -> System.out.println(s.getMessage()));
        }

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
        var flightSeats = flightSeatManager.getAvailableFlightSeats(flight, selectedType);
        var seats = flightSeats.stream().map(seatManager::getSeatForFlightSeat)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        seatNumber.setItems(seats);
        System.out.println(seats);
    }
}
