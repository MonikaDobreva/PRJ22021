package frontend;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Airport;
import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;
import businesslogic.BusinessLogicAPI;
import businesslogic.EditDetailsLogic;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;
    private final EditDetailsLogic eLogic;
   
    private Flight editFlight;

    public editDetailsFlightController(Supplier<SceneManager> sceneManagerSupplier, BusinessLogicAPI bl) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.airportManager = bl.getAirportManager();
        this.airplaneManager = bl.getAirplaneManager();
        this.eLogic = bl.getEditDetailsLogic();
    }

    public void setFlight(Flight f) {
        editFlight = f;
        showFlightDetails();
    }

    private void showFlightDetails() {
        arrivalTime.setText(editFlight.getArrivalTime().toString());
        departureTime.setText(editFlight.getDepartureTime().toString());
        airplane.setText(editFlight.getAirplane());
        price.setText(editFlight.getBasePrice().toPlainString());
        destination.setText(editFlight.getDestinationAirport());
        origin.setText(editFlight.getOriginAirport());
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
     * It updates the flight entry with the changed data in the database.Using
     * the methods from the EditDetailsLogic.
     */
    @FXML
    private void safeEditFlight(ActionEvent event) {
        Map<String, String> safedValues = new HashMap<>();

        //this checks if the boxes are ticked and then puts the data in the map.
        if (chBoxDepTime.isSelected()) {
            safedValues.put("cVDepHour", cVDepHour.getValue().toString());
            safedValues.put("cVDepMin", cVDepMin.getValue().toString());
            safedValues.put("cVDepDate", cVDepDate.getValue().toString());
        }
        if (chBoxArrTime.isSelected()) {
            safedValues.put("cVArrHour", cVArrHour.getValue().toString());
            safedValues.put("cVArrMin", cVArrMin.getValue().toString());
            safedValues.put("cVArrDate", cVArrDate.getValue().toString());
        }
        if (chBoxOrigin.isSelected()) {
            safedValues.put("cVOrigin", cVOrigin.getValue().toString());
        }
        if (chBoxDest.isSelected()) {
            safedValues.put("cVDestination", cVDestination.getValue().toString());
        }
        if (chBoxAirpl.isSelected()) {
            safedValues.put("cVAirplane", cVAirplane.getValue().toString());
        }
        if (chBoxPrice.isSelected()) {
            safedValues.put("cVPrice", cVPrice.getText());
        }

        Map<String, String> returnData = eLogic.passData(safedValues, editFlight);

        if (returnData.containsKey("same")) {
            errorLabel.setText(ResourceBundle.getBundle("frontend.editAisStrings", Locale.getDefault()).getString("nothingChangedLabel"));
        } else if (returnData.containsKey("flightError")) {
            errorLabel.setText(returnData.get("flightError"));
        } else if (returnData.containsKey("updateError")) {
            errorLabel.setText(ResourceBundle.getBundle("frontend.editAisStrings", Locale.getDefault()).getString("somethingWrong"));
        } else if (returnData.containsKey("worked")) {
            Consumer<editFlightController> cons = (editFlightController c) -> c.initWindow();
            sceneManagerSupplier.get().changeScene("editFlights", cons);
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
}
