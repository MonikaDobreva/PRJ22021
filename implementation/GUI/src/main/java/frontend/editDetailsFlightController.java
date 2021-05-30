package frontend;

import businessentitiesapi.Flight;
import java.util.Locale;
import java.util.function.Supplier;
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
    private ComboBox<?> cVOrigin, cVDestination;
    @FXML
    private ComboBox<?> cVAirplane;
    @FXML
    private TextField cVPrice;
    @FXML
    private Button deleteFlightButton, cancelEditButton, safeEditButton;

    private final Supplier<SceneManager> sceneManagerSupplier;
    private final Flight editFlight;

    public editDetailsFlightController(Supplier<SceneManager> sceneManagerSupplier, Flight editFlight) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.editFlight = editFlight;
//        showFlightDetails();//this thrwos errors as well
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
        sceneManagerSupplier.get().changeScene("editFlights");
    }

    /**
     * This method is called when the user clicks on the delteFlight button.
     *
     * It deletes the flight entry from the database.
     */
    @FXML
    private void deleteFlight(ActionEvent event) {
    }

    
     /**
     * This method is called when the user clicks on the safe button.
     *
     * It updates the flight entry with the changed data in the database.
     */
    @FXML
    private void safeEditFlight(ActionEvent event) {
    }

    //    private final int flightID;
//    //    private final String originAirport;
//    //    private final String destinationAirport;
//    //    private final LocalDateTime departureTime;
//    //    private final LocalDateTime arrivalTime;
//    //    private final String airplaneModel;
//    //    private final int basePrice;
//    @FXML
//    private void storeFlight() {
//        try {
//            Flight f = flightManager.createFlight(
//                    1, //placeholder, id should be generated according to the amount of flight already in the database
//                    originApDropdown.getValue(),
//                    destinationApDropdown.getValue(),
//                    LocalDateTime.parse(makeTimeValid(depTimeHourSpinner.getValue().toString()) + ":" + makeTimeValid(depTimeMinSpinner.getValue().toString()) + " " + depTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
//                    LocalDateTime.parse(makeTimeValid(arrTimeHourSpinner.getValue().toString()) + ":" + makeTimeValid(arrTimeMinSpinner.getValue().toString()) + " " + arrTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
//                    airplaneModelDropdown.getValue(),
//                    Integer.parseInt(basePriceField.getText())
//            );
//            flightManager.add(f);
//            nfcLabel.setText("Successfully added flight!");
//        } catch (Exception d) {
//            nfcLabel.setText(d.getMessage());
//        }
//    }
    /**
     * // * Adds an additional 0 to the time if it is only one digit. // *
     * Otherwise the selected time cannot be properly parsed. // * @param t time
     * value // * @return formatted time value //
     */
//    private String makeTimeValid(String t){
//        return t.length() == 1 ? "0" + t : t;
//    }
//     public void listOriginAirports() {
//        originApDropdown.setItems(FXCollections.observableArrayList(
//                airportManager.getAirports().stream()
//                        .map(Airport::getIataCode)
//                        .collect(Collectors.toList())));
//    }
//
//    public void listDestinationAirports() {
//        destinationApDropdown.setItems(FXCollections.observableArrayList(
//                airportManager.getAirportsWithoutOrigin(originApDropdown.getValue()).stream()
//                        .map(Airport::getIataCode)
//                        .collect(Collectors.toList())));
//    }
//
//    public void listAirplaneModels(){
//        //airplaneModelDropdown.setItems((FXCollections.observableArrayList(airplaneManager.getAirplanes().stream().map(a -> a.getAirplaneCode() + " (" + a.getModel() + ")").collect(Collectors.toList()))));
//        airplaneModelDropdown.setItems((FXCollections.observableArrayList(
//                airplaneManager.getAirplanes().stream()
//                        .map(Airplane::getAirplaneCode)
//                        .collect(Collectors.toList()))));
//    }
}
