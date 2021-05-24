package frontend;

import businessentitiesapi.AirplaneManager;
import businessentitiesapi.AirportManager;
import businessentitiesapi.FlightManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.function.Supplier;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class managementDashboardController {

    private final FlightManager flightManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    @FXML
    Label totalFlightsLabel;

    @FXML
    Button updateStatisticsButton;

    public managementDashboardController(FlightManager flightManager, AirportManager airportManager, AirplaneManager airplaneManager){
        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;
        this.flightManager = flightManager;
    }

    @FXML
    private void updateData(){
        totalFlightsLabel.setText(String.valueOf((long) flightManager.getFlights().size()));
    }





}
