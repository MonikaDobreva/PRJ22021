package frontend;

import businessentitiesapi.Flight;
import businesslogic.BusinessLogicAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightController {

    @FXML
    TextField flightName, depTime, arrTime;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights;

    @FXML
    Label flightLabel;

    private BusinessLogicAPI businessLogicAPI;

    public FlightController() {

    }

    public FlightController(BusinessLogicAPI logicAPI) {
        this.businessLogicAPI = logicAPI;
    }

    @FXML
    private void switchToSecondary() throws IOException {
        GUIApp.setRoot("secondary");
    }

    @FXML
    private void storeFlight() {
        Flight f = businessLogicAPI.getFlightManager().createFlight(
                flightName.getText(),
                LocalDate.parse(depTime.getText()),
                LocalDate.parse(arrTime.getText())
        );
        businessLogicAPI.getFlightManager().add(f);
    }


    public void showFlights() {
        var flights = businessLogicAPI.getFlightManager().getFlights();
        StringBuilder flightsListed = new StringBuilder();
        for (Flight f : flights) {
            flightsListed.append("Flight ").append(flights.indexOf(f) + 1).append(": ").append(f.toString()).append("\n");
        }
        flightLabel.setText(flightsListed.toString());
    }
}
