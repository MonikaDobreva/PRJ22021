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
    TextField flightIdField, airplaneIdField, routeIdField, basePriceField;

    @FXML
    DatePicker depTimePicker, arrTimePicker;

    @FXML
    Button StoreFlight, primaryButton, ShowFlights;

    @FXML
    Label nfcLabel;

    @FXML
    ComboBox<Integer> airplaneIdDropdown, routeIdDropdown;

    @FXML
    Spinner<Integer> depTimeHourSpinner, depTimeMinSpinner, arrTimeHourSpinner, arrTimeMinSpinner;

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
                    Integer.parseInt(flightIdField.getText()),
                    LocalDateTime.parse(depTimeHourSpinner.getValue() + ":" + depTimeMinSpinner.getValue() + " " + depTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    LocalDateTime.parse(arrTimeHourSpinner.getValue() + ":" + arrTimeMinSpinner.getValue() + " " + arrTimePicker.getValue(), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")),
                    airplaneIdDropdown.getValue(),
                    Integer.parseInt(routeIdField.getText()),
                    Integer.parseInt(basePriceField.getText())
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
    }

//    public void listFlights() {
//        airplaneIdDropdown.setItems(FXCollections.observableArrayList(flightManager.getFlights().stream().map(Flight::getAirplane).collect(Collectors.toList())));
//    }
    
//    public void listSAirports() {
//        routeIdDropdown.setItems(FXCollections.observableArrayList(flightManager.getFlights().stream().map(Flight::getStartAirport).collect(Collectors.toList())));
//    }
    
//     public void listDEAirports() {
//        dtAirportDropdown.setItems(FXCollections.observableArrayList(flightManager.getFlights().stream().map(Flight::getDestAirport).collect(Collectors.toList())));
//    }
}
