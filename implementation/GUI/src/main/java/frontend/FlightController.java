package frontend;

import businessentitiesapi.*;
import businessentitiesapi.exceptions.FlightStorageException;
import businesslogic.CreateFlightLogic;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightController {


    @FXML
    Label flightIDLabel;

    @FXML
    VBox createFlightWindow;

    @FXML
    TextField basePriceField;

    @FXML
    DatePicker depDatePicker, arrDatePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights, DisplayFlights, storeFlightsButton, backBtn, originAirportInfoBtn,
            destinationAirportInfoBtn, airplaneInfoBtn;

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
    private final CreateFlightLogic createFlightLogic;

    public FlightController(Supplier<SceneManager> sceneManagerSupplier, CreateFlightLogic createFlightLogic) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.createFlightLogic = createFlightLogic;
    }

    @FXML
    public void initWindow(){
        flightIDLabelText();
    }

    @FXML
    public void flightIDLabelText(){
        flightIDLabel.setText(createFlightLogic.getNextID());
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
        try{
            Map<String,String> values = obtainData();
            createFlightLogic.dataValidation(values);
            createFlightLogic.setData(values);

            createFlightLogic.storeFlight();

        } catch (DateTimeParseException | NoSuchElementException | NullPointerException ex){
           showAlert("Warning!", "Some of the fields might be empty! Please have a look :)", AlertType.ERROR);
           createFlightLogic.clearData();
            return;
        } catch (FlightStorageException | IllegalArgumentException ex) {
            showAlert("Warning!", ex.getMessage(), AlertType.ERROR);
            createFlightLogic.clearData();
            return;
        }

        showAlert("Success", "Successfully added flight!", AlertType.INFORMATION);
        clearCreateFlightFields();
    }

    public void showAlert(String title, String message, AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private Map<String, String> obtainData() {
        Map<String,String> values = new HashMap<>();

        values.put("flightID", flightIDLabel.getText().toString());
        values.put("originAirport", originApDropdown.getValue().toString());
        values.put("destinationAirport", destinationApDropdown.getValue().toString());
        values.put("dTHour", depTimeHourSpinner.getValue().toString());
        values.put("dTMin", depTimeMinSpinner.getValue().toString());
        values.put("dTDate", depDatePicker.getValue().toString());
        values.put("aTHour", arrTimeHourSpinner.getValue().toString());
        values.put("aTMin", arrTimeMinSpinner.getValue().toString());
        values.put("aTDate", arrDatePicker.getValue().toString());
        values.put("airplaneInfo", airplaneModelDropdown.getValue().toString());
        values.put("price", basePriceField.getText().toString());

        for (Map.Entry<String, String> value : values.entrySet()){
            System.out.println(value.getKey() + ": " + value.getValue());
        }

        return values;
    }

    /**
     * Helper method which first clears the view with the method clearFlightsView and then
     * assigns the different fields of a flight to the respective columns of the table view in the GUI
     */
    @FXML
    private void showFlights() {
        clearFlightsView();

        var flights = createFlightLogic.obtainFlights();

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
        originApDropdown.setItems(FXCollections.observableArrayList(createFlightLogic.listOriginAirport()));
    }

    /**
     * Helper method which retrieves all the existing destination airports from the database
     * and sets a respective combo box to the resulting collection
     */
    public void listDestinationAirports() {
        destinationApDropdown.setItems(FXCollections.observableArrayList(
                createFlightLogic.listDestinationAirport(originApDropdown.getValue())));
    }

    /**
     * Helper method which retrieves all the existing airplane models from the database
     * and sets a respective combo box to the resulting collection
     */
    public void listAirplaneModels(){
        airplaneModelDropdown.setItems(FXCollections.observableArrayList(createFlightLogic.listAirplanes()));
    }

    /**
     * Clears the view of current flights available displayed in the GUI
     * Assigned to the "Clear View" button on the flight view
     */
    public void clearFlightsView() {
        flightsTable.getItems().clear();
    }

    /**
     * Clears the fields of the Create Flights window
     */
    public void clearCreateFlightFields() {
        flightIDLabelText();
        depTimeHourSpinner.getValueFactory().setValue(0);
        depTimeMinSpinner.getValueFactory().setValue(0);
        arrTimeHourSpinner.getValueFactory().setValue(0);
        arrTimeMinSpinner.getValueFactory().setValue(0);
        depDatePicker.getEditor().clear();
        arrDatePicker.getEditor().clear();
        originApDropdown.getSelectionModel().clearSelection();
        destinationApDropdown.getSelectionModel().clearSelection();
        airplaneModelDropdown.getSelectionModel().clearSelection();
        basePriceField.setText("00.00");
        originAirportInfoBtn.setVisible(false);
        destinationAirportInfoBtn.setVisible(false);
        airplaneInfoBtn.setVisible(false);
    }

    public void displayOriginAirportInfo(){

    }

    public void displayDestinationAirportInfo(){

    }

    public void displayAirplaneInfo(){

    }

    public void clearFieldsWithConfirmation(){
        Alert alert = new Alert(AlertType.CONFIRMATION, "Clear the window fields ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            clearCreateFlightFields();
        }
    }
}
