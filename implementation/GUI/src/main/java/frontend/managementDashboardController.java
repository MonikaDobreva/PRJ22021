package frontend;

import businessentitiesapi.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class managementDashboardController {

    private final FlightManager flightManager;
    private final BookingManager bookingManager;
    private final FlightRouteManager flightRouteManager;
    private final TicketManager ticketManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    @FXML
    Label totalFlightsLabel, totalRoutesLabel, totalBookingsLabel, mDashDepLabel, mDashArrLabel, mDashEstFDLabel,
            mDashBookingsLabel, mDashMealsLabel, totalTicketsLabel, totalRevenueLabel;

    @FXML
    Button updateStatisticsButton;

    @FXML
    ComboBox<Integer> mDashFlightDropdown, mDashBookingDropdown;

    public managementDashboardController(
            FlightManager flightManager,
            AirportManager airportManager,
            AirplaneManager airplaneManager,
            BookingManager bookingManager,
            FlightRouteManager flightRouteManager,
            TicketManager ticketManager
    ) {
        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;
        this.flightManager = flightManager;
        this.bookingManager = bookingManager;
        this.flightRouteManager = flightRouteManager;
        this.ticketManager = ticketManager;
    }

    /**
     * Helper method to use as an action which ensures that all the necessary KPI's computed from values from
     * the database are displayed inside the grid pane
     */
    @FXML
    private void updateFlightStatistics() {
        totalFlightsLabel.setText(String.valueOf(flightManager.getFlights().size()));
        totalRoutesLabel.setText(String.valueOf(flightRouteManager.getFlightRoutes().size()));
        totalBookingsLabel.setText(String.valueOf(bookingManager.getBookings().size()));
        totalTicketsLabel.setText(String.valueOf(ticketManager.getTickets().size()));
        totalRevenueLabel.setText(String.valueOf(sumOfTicketPrices()));

    }

    public double sumOfTicketPrices(){
        var allTickets = ticketManager.getTickets();
        double sum = 0;
        System.out.println(allTickets);
        for (Ticket t : allTickets) {
            sum = sum + t.getPricePaid();
        }
        return sum;
    }

    /**
     * Method used as an action to set the values of the dropdown menu for the currently
     * registered flights from the database. Furthermore, when clicked it sets the displayed value of the adjacent
     * dropdown to null
     */
    @FXML
    private void listFlights() {
        mDashFlightDropdown.setItems(FXCollections.observableArrayList(
                flightManager.getFlights().stream()
                        .map(Flight::getFlightID)
                        .collect(Collectors.toList())));
        mDashBookingDropdown.setValue(null);
    }


    @FXML
    private void listFlightsDependingOnSelectedFlight() {
        var currentFlight = mDashFlightDropdown.getValue();

        if (currentFlight == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Excuse me!");
            alert.setTitle("Information");
            alert.setContentText("Please select a flight first!");
            alert.showAndWait();
        } else {
            if (bookingManager.getBookingsOfFlight(currentFlight).isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Excuse me!");
                alert.setTitle("Information");
                alert.setContentText("No bookings for this flight available!");
            } else {
                mDashBookingDropdown.setItems(FXCollections.observableArrayList(
                        bookingManager.getBookingsOfFlight(currentFlight).stream()
                                .map(Booking::getBookingId)
                                .distinct()
                                .collect(Collectors.toList())
                ));
            }
        }
    }

    @FXML
    private void updateFlightData() {
        var currentFlight = mDashFlightDropdown.getValue();
        mDashDepLabel.setText("1");
        mDashArrLabel.setText("2");
        mDashEstFDLabel.setText("3");
        mDashBookingsLabel.setText("4");
        mDashMealsLabel.setText("5");

    }


}
