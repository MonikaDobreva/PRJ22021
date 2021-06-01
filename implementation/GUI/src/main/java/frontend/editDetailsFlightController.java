package frontend;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Rachel
 */
public class editDetailsFlightController {

    @FXML
    private Spinner<Integer> cVDepHour, cVDepMin, cVArrHour, cVArrMin;
    @FXML
    private DatePicker cVDepDate, cVArrDate;
    @FXML
    private Label price, departureTime, arrivalTime, origin, destination, airplane, errorLabel;
    @FXML
    private CheckBox chBoxDepTime, chBoxArrTime, chBoxOrigin, chBoxDest, chBoxAirpl, chBoxPrice;
    @FXML
    private ComboBox<String> cVOrigin, cVDestination;
    @FXML
    private ComboBox<String> cVAirplane;
    @FXML
    private TextField cVPrice;
    @FXML
    private Button deleteFlightButton, cancelEditButton, safeEditButton;

    private final Supplier<SceneManager> sceneManagerSupplier;
    private final FlightManager flightManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;
    private Flight editFlight;

    public editDetailsFlightController(Supplier<SceneManager> sceneManagerSupplier, FlightManager flightManager, AirportManager airportManager, AirplaneManager airplaneManager) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.flightManager = flightManager;
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
    }

    public void setFlight(Flight f) {
        editFlight = f;
        showFlightDetails();
    }

    private void showFlightDetails() {
        arrivalTime.setText(editFlight.getArrivalTime().toString());
    }

    /**
     * CheckboxDepartureTime: on clicking the method is called.
     *
     * This makes on checked box the edit values for the departure time visible.
     */
    @FXML
    private void showCVDepTime(ActionEvent event) {
        if (chBoxDepTime.isSelected()) {
            cVDepHour.setVisible(true);
            cVDepMin.setVisible(true);
            cVDepDate.setVisible(true);
        }
        if (!chBoxDepTime.isSelected()) {
            cVDepHour.setVisible(false);
            cVDepMin.setVisible(false);
            cVDepDate.setVisible(false);
        }
    }

    /**
     * CheckboxArrivalTime: on clicking the method is called.
     *
     * This makes on checked box the edit values for the arrival time visible.
     */
    @FXML
    private void showCVArrTime(ActionEvent event) {
        if (chBoxArrTime.isSelected()) {
            cVArrHour.setVisible(true);
            cVArrMin.setVisible(true);
            cVArrDate.setVisible(true);
        }
        if (!chBoxArrTime.isSelected()) {
            cVArrHour.setVisible(false);
            cVArrMin.setVisible(false);
            cVArrDate.setVisible(false);
        }
    }

    @FXML
    private void showCVOrigin(ActionEvent event) {
        if (chBoxOrigin.isSelected()) {
            cVOrigin.setVisible(true);
        }
        if (!chBoxOrigin.isSelected()) {
            cVOrigin.setVisible(false);
        }
    }

    @FXML
    private void showCVDest(ActionEvent event) {
        if (chBoxDest.isSelected()) {
            cVDestination.setVisible(true);
        }
        if (!chBoxDest.isSelected()) {
            cVDestination.setVisible(false);
        }
    }

    @FXML
    private void showCVAirpl(ActionEvent event) {
        if (chBoxAirpl.isSelected()) {
            cVAirplane.setVisible(true);
        }
        if (!chBoxAirpl.isSelected()) {
            cVAirplane.setVisible(false);
        }
    }

    @FXML
    private void showCVPrice(ActionEvent event) {
        if (chBoxPrice.isSelected()) {
            cVPrice.setVisible(true);
        }
        if (!chBoxPrice.isSelected()) {
            cVPrice.setVisible(false);
        }
    }

    @FXML
    private void cancelChangeScene(ActionEvent event) {
        Consumer<editFlightController> cons = (editFlightController c) -> c.initWindow();
        sceneManagerSupplier.get().changeScene("editFlights", cons);
    }

    /**
     * This method is called when the user clicks on the delteFlight button.
     *
     * It redirects to the delete flight confirmation page
     */
    @FXML
    private void deleteFlight(ActionEvent event) {
        Consumer<DeleteFlightController> cont = (DeleteFlightController c) -> c.setDeleteFlight(editFlight);
        sceneManagerSupplier.get().changeScene("deleteFlight", cont);
    }

    /**
     * This method is called when the user clicks on the safe button.
     *
     * It updates the flight entry with the changed data in the database.
     */
    @FXML
    private void safeEditFlight(ActionEvent event) {
        //helper var for easier reading
        boolean changed = false;
        String originAirport = editFlight.getOriginAirport();
        String destinationAirport = editFlight.getDestinationAirport();
        LocalDateTime depTime = editFlight.getDepartureTime();
        LocalDateTime arrTime = editFlight.getArrivalTime();
        String airplane = editFlight.getAirplane();
        BigDecimal basePrice = editFlight.getBasePrice();

        //this checks if the boxes are ticked and uses then the new data for the flight other wise it will be the old flight
        if (chBoxDepTime.isSelected()) {
            changed = true;
            depTime = LocalDateTime.parse(
                    makeTimeValid(cVDepHour.getValue().toString())
                    + ":"
                    + makeTimeValid(cVDepMin.getValue().toString())
                    + " "
                    + cVDepDate.getValue(),
                    DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
        }
        if (chBoxArrTime.isSelected()) {
            changed = true;
            arrTime = LocalDateTime.parse(
                    makeTimeValid(cVArrHour.getValue().toString())
                    + ":"
                    + makeTimeValid(cVArrMin.getValue().toString())
                    + " "
                    + cVArrDate.getValue(),
                    DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
        }
        if (chBoxOrigin.isSelected()) {
            changed = true;
            originAirport = cVOrigin.getValue();
        }
        if (chBoxDest.isSelected()) {
            changed = true;
            destinationAirport = cVDestination.getValue();
        }
        if (chBoxAirpl.isSelected()) {
            changed = true;
            airplane = cVAirplane.getValue();
        }
        if (chBoxPrice.isSelected()) {
            changed = true;
            basePrice = new BigDecimal(cVPrice.getText());
        }
        //if nothing is changed let the user know
        if (changed == false) {
            errorLabel.setText(ResourceBundle.getBundle("frontend.editAisStrings", Locale.getDefault()).getString("nothingChangedLabel"));
            return;
        }

        try {
            Flight f = flightManager.createFlight(1, //placeholder, id should be generated according to the amount of flight already in the database
                    originAirport, destinationAirport, depTime, arrTime, airplane, basePrice);
            flightManager.update(f);
//rember to check update............
            Consumer<editFlightController> cons = (editFlightController c) -> c.initWindow();
            sceneManagerSupplier.get().changeScene("editFlights", cons);
            
        } catch (Exception e) {
            errorLabel.setText(e.getLocalizedMessage());
        }
    }

    /**
     * show start airports
     *
     * @param event
     */
    @FXML
    private void showStart(MouseEvent event) {
        cVOrigin.setItems(FXCollections.observableArrayList(
                airportManager.getAirports().stream()
                        .map(Airport::getIataCode)
                        .collect(Collectors.toList())));
    }

    /**
     * show destination airports
     *
     * @param event
     */
    @FXML
    private void showDestination(MouseEvent event) {
        cVDestination.setItems(FXCollections.observableArrayList(
                airportManager.getAirportsWithoutOrigin(cVOrigin.getValue()).stream()
                        .map(Airport::getIataCode)
                        .collect(Collectors.toList())));
    }

    @FXML
    private void showAirplanes(MouseEvent event) {
        cVAirplane.setItems((FXCollections.observableArrayList(
                airplaneManager.getAirplanes().stream()
                        .map(Airplane::getAirplaneCode)
                        .collect(Collectors.toList()))));
    }

    /**
     * Small helper method, which adds an additional 0 to the time if it is only
     * one digit. Otherwise the selected time cannot be properly parsed.
     *
     * @param t time value
     * @return formatted time value
     */
    private String makeTimeValid(String t) {
        return t.length() == 1 ? "0" + t : t;
    }

}
