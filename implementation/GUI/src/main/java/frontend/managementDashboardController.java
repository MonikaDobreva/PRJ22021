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
    private final BookingManager bookingManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    @FXML
    Label totalFlightsLabel, totalRoutesLabel, totalBookingsLabel, mDashDepLabel, mDashArrLabel, mDashEstFDLabel,
            mDashBookingsLabel, mDashMealsLabel, totalTicketsLabel, totalRevenueLabel;

    @FXML
    Button updateStatisticsButton;

    @FXML
    ComboBox<Integer> mDashFlightDropdown, mDashBookingDropdown;

    public managementDashboardController(FlightManager flightManager, AirportManager airportManager, AirplaneManager airplaneManager, BookingManager bookingManager){
        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;
        this.flightManager = flightManager;
        this.bookingManager = bookingManager;
    }

    @FXML
    private void updateFlightStatistics(){
        totalFlightsLabel.setText(String.valueOf(flightManager.getFlights().size()));
        totalRoutesLabel.setText("Test");
        totalBookingsLabel.setText(String.valueOf(bookingManager.getBookings().size()));
        totalTicketsLabel.setText("Test");

    }

    @FXML
    private void listFlights(){
        mDashFlightDropdown.setItems(FXCollections.observableArrayList(
                flightManager.getFlights().stream()
                        .map(Flight::getFlightID)
                        .collect(Collectors.toList())));
    }



    @FXML
    private void listFlightsDependingOnSelectedFlight(){
        var currentFlight = mDashFlightDropdown.getValue();
        mDashBookingDropdown.setItems(FXCollections.observableArrayList(
                bookingManager.getBookingsOfFlight(currentFlight).stream()
                .map(Booking::getBookingId)
                .collect(Collectors.toList())
        ));
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
