package frontend;

import businessentitiesapi.*;
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
import java.util.NoSuchElementException;
import java.util.Optional;
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
    DatePicker depDatePicker, arrDatePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights, DisplayFlights, storeFlightsButton, backBtn;

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
    private final FlightRouteManager flightRouteManager;

    public FlightController(Supplier<SceneManager> sceneManagerSupplier, FlightManager flightManager,
                            AirportManager airportManager, AirplaneManager airplaneManager, FlightRouteManager flightRouteManager) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.flightManager = flightManager;
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
        this.flightRouteManager = flightRouteManager;
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
        //TODO: Separate code in smaller pieces - Look cleaner
        Optional<Flight>f = Optional.empty();
        Optional<Integer> flightID = Optional.empty();
        Optional<String> originAirport = Optional.empty();
        Optional<String> destinationAirport = Optional.empty();
        Optional<LocalDateTime> departureTime = Optional.empty();
        Optional<LocalDateTime> arrivalTime = Optional.empty();;
        Optional<String> airplaneInfo = Optional.empty();
        Optional<BigDecimal> price = Optional.empty();

        //Obtain values from all the fields -
        //Catch exceptions and show message in case some of the fields are empty
        try{
            flightID = Optional.of(Integer.parseInt(flightIDLabel.getText()));
            originAirport = Optional.of(originApDropdown.getValue());
            destinationAirport = Optional.of(destinationApDropdown.getValue());
            departureTime = Optional.of(LocalDateTime.parse(makeTimeValid(depTimeHourSpinner.getValue().toString())
                    + ":" + makeTimeValid(depTimeMinSpinner.getValue().toString()) + " "
                    + depDatePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")));
            arrivalTime = Optional.of(LocalDateTime.parse(makeTimeValid(arrTimeHourSpinner.getValue().toString())
                    + ":" + makeTimeValid(arrTimeMinSpinner.getValue().toString()) + " "
                    + arrDatePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")));
            airplaneInfo = Optional.of(airplaneModelDropdown.getValue());
            price = Optional.of(new BigDecimal(basePriceField.getText()));
        } catch (DateTimeParseException | NoSuchElementException | NullPointerException ex){
            showAlert("Warning!", "Some of the fields might be empty! Please have a look :)", AlertType.ERROR);
            return;
        }

        //Check availability for select airplane on desired dates
        //Catch exception and show message in case of not available
        Airplane airplane = airplaneManager.getAirplane(airplaneInfo.get().split(" ")[0]);

        try{
            airplaneManager.checkAvailability(airplane, departureTime.get(), arrivalTime.get());
        } catch (IllegalArgumentException ex){
            showAlert("Warning!", ex.getMessage(), AlertType.ERROR);
            return;
        }

        //With the data collected try to create the Flight object
        //Catch exception and show message in case of violation of flight constraints
        try {
            f = Optional.of(flightManager.createFlight(
                    flightID.get(),
                    originAirport.get(),
                    destinationAirport.get(),
                    departureTime.get(),
                    arrivalTime.get(),
                    airplane.getModel(),
                    price.get()
            ));
        } catch (IllegalArgumentException ex) {
            showAlert("Warning!", ex.getMessage(), AlertType.ERROR);
            return;
        }

        flightRouteManager.checkExistence(originAirport.get(), destinationAirport.get());

        flightManager.add(f.get());
        showAlert("Success", "Successfully added flight!", AlertType.INFORMATION);
    }

    public void showAlert(String title, String message, AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
                        .map(a -> a.getAirplaneCode() + " (" + a.getModel() + ")")
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
