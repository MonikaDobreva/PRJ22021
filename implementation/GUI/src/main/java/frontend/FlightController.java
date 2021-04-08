package frontend;

import businessentitiesapi.*;
import businesslogic.BusinessLogicAPI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
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
    TextField flightName, depTime, arrTime, airplane, startAirport, destAirport;

    @FXML
    DatePicker depTimePicker, arrTimePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights;

    @FXML
    Label flightLabel, nfcLabel;

    @FXML
    ComboBox<String> airplaneDropdown;

    @FXML
    ComboBox<String> stAirportDropdown, dtAirportDropdown;

    private final Supplier<SceneManager> sceneManagerSupplier;
    private final FlightManager flightManager;

    public FlightController( Supplier<SceneManager> sceneManagerSupplier, FlightManager flightManager) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.flightManager = flightManager;
    }

    @FXML
    private void switchToSecondary() throws IOException {
        sceneManagerSupplier.get().changeScene( "secondary" );
    }

    @FXML
    private void backToStart() throws IOException {
        sceneManagerSupplier.get().changeScene( "welcome" );
    }


    @FXML
    private void storeFlight() {
        try {
            Flight f = flightManager.createFlight(
                    flightName.getText(),
                    LocalDateTime.parse(depTime.getText() + " " + depTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    LocalDateTime.parse(arrTime.getText() + " " + arrTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    airplaneDropdown.getValue(),
                    startAirport.getText(),
                    destAirport.getText()
                   // airplaneDropdown.getValue(),
//                    stAirportDropdown.getValue(),
//                    dtAirportDropdown.getValue()
            );
            flightManager.add(f);
            nfcLabel.setText("Successfully added flight!");
        } catch (Exception d) {
            nfcLabel.setText("Invalid input! Please try again.");
        }
    }

    @FXML
    private void showFlights() {
        var flights = flightManager.getFlights();
        StringBuilder flightsListed = new StringBuilder();
        for (Flight f : flights) {
            flightsListed.append("Flight ").append(flights.indexOf(f) + 1).append(": ").append(f.toString()).append("\n");
        }
        flightLabel.setText(flightsListed.toString());
    }

    public void listFlights() {
        airplaneDropdown.setItems(FXCollections.observableArrayList(flightManager.getFlights().stream().map(Flight::getAirplane).collect(Collectors.toList())));
    }
}
