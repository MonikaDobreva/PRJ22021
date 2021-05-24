package frontend;

import businessentitiesapi.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class managementDashboardController {

    private final FlightManager flightManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    @FXML
    Label totalFlightsLabel, totalRoutesLabel, totalBookingsLabel, mDashDepLabel, mDashArrLabel, mDashEstFDLabel, mDashBookingsLabel, mDashMealsLabel;

    @FXML
    Button updateStatisticsButton;

    @FXML
    ComboBox<Integer> mDashFlightDropdown;

    public managementDashboardController(FlightManager flightManager, AirportManager airportManager, AirplaneManager airplaneManager){
        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;
        this.flightManager = flightManager;
    }

    @FXML
    private void updateData(){
        totalFlightsLabel.setText(String.valueOf((long) flightManager.getFlights().size()));
        totalRoutesLabel.setText("Test");
        totalBookingsLabel.setText("Test");
    }

    @FXML
    private void listFlights(){
        mDashFlightDropdown.setItems(FXCollections.observableArrayList(
                flightManager.getFlights().stream()
                        .map(Flight::getFlightID)
                        .collect(Collectors.toList())));
    }

    @FXML
    private void updateFlightData(){
        var currentFlight = mDashFlightDropdown.getValue();
        mDashDepLabel.setText("1");
        mDashArrLabel.setText("2");
        mDashEstFDLabel.setText("3");
        mDashBookingsLabel.setText("4");
        mDashMealsLabel.setText("5");

    }





}
