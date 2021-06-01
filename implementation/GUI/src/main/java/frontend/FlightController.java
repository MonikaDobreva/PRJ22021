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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;
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
    Label airplaneModelLabel = new Label(), flightIDLabel, originAirportLabel = new Label(), destinationAirportLabel = new Label();

    @FXML
    VBox createFlightWindow;

    @FXML
    TextField basePriceField;

    @FXML
    DatePicker depTimePicker, arrTimePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights, DisplayFlights, clearFlightsButton, backBtn;

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
    public void initWindow(){
        flightIDLabelText();
    }

    @FXML
    public void flightIDLabelText(){
        flightIDLabel.setText(String.valueOf(flightManager.getLastID() + 1));
    }

    /**
     * Method assigned as an action to buttons which bring the user back to the welcome page
     * @throws IOException
     */
    @FXML
    private void backToStart() throws IOException {
        sceneManagerSupplier.get().changeScene("salesOfficerOptions");
    }

    /**
     * Method assigned as an action to buttons which should bring the use to the flight view
     * @throws IOException
     */
    @FXML
    private void switchToViewFlight() throws IOException {
        sceneManagerSupplier.get().changeScene("viewFlights");
    }

    /**
     * Method assigned as an action to a button which finalizes the creation of a flight inputted through the
     * "create flight view" and adds it to the database
     */
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
                    new BigDecimal(basePriceField.getText())
            );
            flightManager.add(f);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added flight!");
            alert.showAndWait();

        } catch (Exception d) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText(d.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Small helper method, which adds an additional 0 to the time if it is only one digit.
     * Otherwise the selected time cannot be properly parsed.
     * @param t time value
     * @return formatted time value
     */
    private String makeTimeValid(String t){
        return t.length() == 1 ? "0" + t : t;
    }

    /**
     * Helper method which first clears the view with the method clearFlightsView and then
     * assigns the different fields of a flight to the respective columns of the table view in the GUI
     */
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


    /**
     * Helper method which retrieves all the existing origin airports from the database
     * and sets a respective combo box to the resulting collection
     */
    public void listOriginAirports() {
        originApDropdown.setItems(FXCollections.observableArrayList(
                airportManager.getAirports().stream()
                        .map(Airport::getIataCode)
                        .collect(Collectors.toList())));
    }

    /**
     * Helper method which retrieves all the existing destination airports from the database
     * and sets a respective combo box to the resulting collection
     */
    public void listDestinationAirports() {
        destinationApDropdown.setItems(FXCollections.observableArrayList(
                airportManager.getAirportsWithoutOrigin(originApDropdown.getValue()).stream()
                        .map(Airport::getIataCode)
                        .collect(Collectors.toList())));
    }

    /**
     * Helper method which retrieves all the existing airplane models from the database
     * and sets a respective combo box to the resulting collection
     */
    public void listAirplaneModels(){
        airplaneModelDropdown.setItems((FXCollections.observableArrayList(
                airplaneManager.getAirplanes().stream()
                        .map(Airplane::getAirplaneCode)
                        .collect(Collectors.toList()))));
    }

    /**
     * Clears the view of current flights available displayed in the GUI
     * Assigned to the "Clear View" button on the flight view
     */
    public void clearFlightsView() {
        flightsTable.getItems().clear();
    }

}
