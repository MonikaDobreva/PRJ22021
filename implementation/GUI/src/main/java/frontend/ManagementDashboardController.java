package frontend;

import businessentitiesapi.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class ManagementDashboardController {

    private final FlightManager flightManager;
    private final BookingManager bookingManager;
    private final FlightRouteManager flightRouteManager;
    private final TicketManager ticketManager;
    private final MealTypeManager mealTypeManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    @FXML
    Label totalFlightsLabel, totalRoutesLabel, totalBookingsLabel, mDashDepLabel, mDashArrLabel, mDashEstFDLabel,
            mDashBookingsLabel, mDashMealsLabel, totalTicketsLabel, totalRevenueLabel, mDashBookedByLabel, mDashGoingToLabel,
            routesTabTotalRoutesLabel, routesTabTotalPlanesLabel, routesTabTotalFlightsLabel, popularMealLabel,
            amountPopMeal;

    @FXML
    Button updateStatisticsButton;

    @FXML
    ComboBox<Integer> mDashFlightDropdown, mDashBookingDropdown, mDashRoutesDropdown;

    public ManagementDashboardController(
            FlightManager flightManager,
            AirportManager airportManager,
            AirplaneManager airplaneManager,
            BookingManager bookingManager,
            FlightRouteManager flightRouteManager,
            TicketManager ticketManager,
            MealTypeManager mealTypeManager
    ) {
        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;
        this.flightManager = flightManager;
        this.bookingManager = bookingManager;
        this.flightRouteManager = flightRouteManager;
        this.ticketManager = ticketManager;
        this.mealTypeManager = mealTypeManager;
    }

    //////////////////////////////////// General Tab ////////////////////////////////////////////////////////////////

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
        popularMealLabel.setText(getMostBookedMeal().getMealName());
        amountPopMeal.setText(getAmountOfPopularMeal() + " times ordered");

    }

    /**
     * Helper method to calculate the sum of all the tickets currently sold
     *
     * @return the value of the sum as a BigDecimal
     */
    public BigDecimal sumOfTicketPrices() {
        return ticketManager.getSumOfTicketPrices();
    }

    public MealType getMostBookedMeal() {
        return mealTypeManager.getMostBookedMeal();
    }

    public int getAmountOfPopularMeal() {
        return mealTypeManager.getAmountOfPopularMeal(getMostBookedMeal().getId());
    }

    //////////////////////////// Bookings Tab /////////////////////////////////////////////////////////////////////////

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


    /**
     * Helper method to list all the bookings connected to the previous selected flight
     * inside of this dropdown menu. If there is no booking for the flight available a respective alert
     * dialog will appear
     */
    @FXML
    private void listFlightsDependingOnSelectedFlight() {
        var currentFlight = mDashFlightDropdown.getValue();
        if (currentFlight == null) {
            showAlert("Information", "Please select a flight first!", AlertType.INFORMATION);
        } else if (bookingManager.getBookingsOfFlight(currentFlight).isEmpty()) {
            showAlert("Information", "No bookings for this flight available!", AlertType.INFORMATION);
        } else {
            mDashBookingDropdown.setItems(FXCollections.observableArrayList(
                    bookingManager.getBookingsOfFlight(currentFlight).stream()
                            .map(Booking::getBookingId)
                            .distinct()
                            .collect(Collectors.toList())
            ));
        }
    }

    /**
     * Helper method to update the flight data for the booking tab in the tab pane
     */
    @FXML
    private void updateFlightData() {
        var selectedFlightID = mDashFlightDropdown.getValue();
        var selectedFlightAsObject = flightManager.getFlights().stream()
                .filter(f -> f.getFlightID() == selectedFlightID).findFirst().get();
        var selectedBookingID = mDashBookingDropdown.getValue();
        var selectedBookingAsObject = bookingManager.getBookingsOfFlight(selectedFlightID).stream()
                .filter(b -> b.getBookingId() == selectedBookingID).findFirst().get();

        if (mDashFlightDropdown.getValue() == null && mDashBookingDropdown.getValue() == null) {
            showAlert("Information", "Please select at least a flight first!", AlertType.INFORMATION);
        }
        if (mDashBookingDropdown.getValue() == null) {
            mDashDepLabel.setText("1");
            mDashArrLabel.setText("2");
            mDashEstFDLabel.setText("3");
            mDashBookingsLabel.setText("4");
            mDashMealsLabel.setText("5");
        } else {
            mDashDepLabel.setText(selectedFlightAsObject.getOriginAirport());
            mDashArrLabel.setText(selectedFlightAsObject.getDestinationAirport());
            mDashEstFDLabel.setText(calcEST(selectedFlightAsObject.getDepartureTime(), selectedFlightAsObject.getArrivalTime()) + " minutes");
            mDashBookingsLabel.setText(String.valueOf(bookingManager.getBookingsOfFlight(selectedFlightID).size()));
            mDashMealsLabel.setText("5");

            mDashGoingToLabel.setText("only booking");
            mDashBookedByLabel.setText("");
        }
    }

    public long calcEST(LocalDateTime start, LocalDateTime end) {
        Duration d = Duration.between(start, end);
        return d.toMinutes();
    }

    /**
     * Clears the view of the statistics by setting the text of the labels to an empty string
     */
    public void clearStatistics() {
        mDashDepLabel.setText("");
        mDashArrLabel.setText("");
        mDashEstFDLabel.setText("");
        mDashBookingsLabel.setText("");
        mDashMealsLabel.setText("");

        mDashGoingToLabel.setText("");
        mDashBookedByLabel.setText("");
    }

    /**
     * Helper method to create an Alert with all the necessary information with an one-liner
     *
     * @param title   The title of the message
     * @param message The message itself
     * @param type    The type of the alert, determines the icon
     */
    public void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    ////////////////////////////////// routes tab methods //////////////////////////////////////////////////////////

    @FXML
    public void listRoutes() {
        mDashRoutesDropdown.setItems(FXCollections.observableArrayList(
                flightRouteManager.getFlightRoutes().stream()
                        .map(FlightRoute::getFlightRouteID).collect(Collectors.toList())
        ));
    }

    @FXML
    public void routesTabApply() {
        var selectedRouteId = mDashRoutesDropdown.getValue();
        var selectedRouteAsObject = flightRouteManager.getFlightRoutes().stream()
                .filter(r -> r.getFlightRouteID() == selectedRouteId).findFirst().get();
        if (mDashRoutesDropdown.getValue() == null) {
            routesTabTotalRoutesLabel.setText(String.valueOf(flightRouteManager.getFlightRoutes().size()));
        } else if (mDashRoutesDropdown.getValue() != null) {
            routesTabTotalRoutesLabel.setText("0");
            routesTabTotalFlightsLabel.setText(String.valueOf(flightManager.getFlightsByRouteId(selectedRouteId).size()));
            routesTabTotalPlanesLabel.setText("0");

        }
    }


}
