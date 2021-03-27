package frontend;

import businessentitiesapi.Flight;
import businesslogic.BusinessLogicAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class FlightController {


    @FXML
    TextField flightName, depTime, arrTime, airplane, startAirport, destAirport;

    @FXML
    DatePicker depTimePicker, arrTimePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights;

    @FXML
    Label flightLabel, nfcLabel;

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
        try {
            Flight f = businessLogicAPI.getFlightManager().createFlight(
                    flightName.getText(),
                    LocalDateTime.parse(depTime.getText() + " " + depTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    LocalDateTime.parse(arrTime.getText() + " " + arrTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    airplane.getText(),
                    startAirport.getText(),
                    destAirport.getText()
            );
            flightLabel.setText(f.toString());
            businessLogicAPI.getFlightManager().add(f);
            nfcLabel.setText("Successfully added flight!");
        } catch (Exception d) {
            nfcLabel.setText("Invalid input! Please try again.");
        }
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
