package frontend;

import businessentitiesapi.*;
import businesslogic.BusinessLogicAPI;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightController {


    @FXML
    TextField airplaneModelField, basePriceField;

    @FXML
    DatePicker depTimePicker, arrTimePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights, DisplayFlights, clearFlightsButton;

    @FXML
    Label nfcLabel;

    @FXML
    ComboBox<String> originApDropdown, destinationApDropdown, airplaneModelDropdown;

    @FXML
    Spinner<Integer> depTimeHourSpinner, depTimeMinSpinner, arrTimeHourSpinner, arrTimeMinSpinner;

    @FXML
    TableView<Flight> flightsTable = new TableView<>();

    @FXML
    TableColumn<Flight, Integer> flightIdColumn = new TableColumn<>(), basePriceColumn = new TableColumn<>();

    @FXML
    TableColumn<Flight, String> originAirportColumn = new TableColumn<>(),
            destinationAirportColumn = new TableColumn<>(),
            airplaneColumn = new TableColumn<>();

    @FXML
    TableColumn<Flight, LocalDateTime> departureTimeColumn = new TableColumn<>(),
            arrivalTimeColumn = new TableColumn<>();

    private final Supplier<SceneManager> sceneManagerSupplier;
    private final FlightManager flightManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    public FlightController(Supplier<SceneManager> sceneManagerSupplier, FlightManager flightManager, AirportManager airportManager, AirplaneManager airplaneManager) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.flightManager = flightManager;
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
    }

    @FXML
    private void backToStart() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void switchToViewFlight() throws IOException {
        sceneManagerSupplier.get().changeScene("viewFlights");
    }

    //    private final int flightID;
    //    private final String originAirport;
    //    private final String destinationAirport;
    //    private final LocalDateTime departureTime;
    //    private final LocalDateTime arrivalTime;
    //    private final String airplaneModel;
    //    private final int basePrice;
    @FXML
    private void storeFlight() {
        try {
            Flight f = flightManager.createFlight(
                    1, //placeholder, id should be generated according to the amount of flight already in the database
                    originApDropdown.getValue(),
                    destinationApDropdown.getValue(),
                    LocalDateTime.parse(makeTimeValid(depTimeHourSpinner.getValue().toString()) + ":" + makeTimeValid(depTimeMinSpinner.getValue().toString()) + " " + depTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    LocalDateTime.parse(makeTimeValid(arrTimeHourSpinner.getValue().toString()) + ":" + makeTimeValid(arrTimeMinSpinner.getValue().toString()) + " " + arrTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    airplaneModelDropdown.getValue(),
                    Integer.parseInt(basePriceField.getText())
            );
            flightManager.add(f);
            nfcLabel.setText("Successfully added flight!");
        } catch (Exception d) {
            nfcLabel.setText(d.getMessage());
        }
    }

    /**
     * Adds an additional 0 to the time if it is only one digit.
     * Otherwise the selected time cannot be properly parsed.
     * @param t time value
     * @return formatted time value
     */
    private String makeTimeValid(String t){
        return t.length() == 1 ? "0" + t : t;
    }

    @FXML
    private void clearNfcLabel(){
        nfcLabel.setText("");
    }

    @FXML
    private void showFlights() {
        clearFlightsView();
        var flights = flightManager.getFlights();
        flightsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        flightsTable.getItems().addAll(flights);
        flightIdColumn.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<>("originAirport"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        airplaneColumn.setCellValueFactory(new PropertyValueFactory<>("airplane"));
        basePriceColumn.setCellValueFactory(new PropertyValueFactory<>("basePrice"));
    }


    public void listOriginAirports() {
        originApDropdown.setItems(FXCollections.observableArrayList(
                airportManager.getAirports().stream()
                        .map(Airport::getIataCode)
                        .collect(Collectors.toList())));
    }

    public void listDestinationAirports() {
        destinationApDropdown.setItems(FXCollections.observableArrayList(
                airportManager.getAirportsWithoutOrigin(originApDropdown.getValue()).stream()
                        .map(Airport::getIataCode)
                        .collect(Collectors.toList())));
    }

    public void listAirplaneModels(){
        //airplaneModelDropdown.setItems((FXCollections.observableArrayList(airplaneManager.getAirplanes().stream().map(a -> a.getAirplaneCode() + " (" + a.getModel() + ")").collect(Collectors.toList()))));
        airplaneModelDropdown.setItems((FXCollections.observableArrayList(
                airplaneManager.getAirplanes().stream()
                        .map(Airplane::getAirplaneCode)
                        .collect(Collectors.toList()))));
    }

    public void clearFlightsView() {
        flightsTable.getItems().clear();
    }
}
