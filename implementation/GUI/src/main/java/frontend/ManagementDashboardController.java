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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final PersonManager personManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;

    @FXML
    Label totalFlightsLabel, totalRoutesLabel, totalBookingsLabel, mDashDepLabel, mDashArrLabel, mDashEstFDLabel,
            mDashBookingsLabel, mDashMealsLabel, totalTicketsLabel, totalRevenueLabel, mDashBookedByLabel, bookingTabBookedOnLabel,
            popularMealLabel, generalTabTotalAirports, generalTabPopularAirport, generalTabMostCapacity, generalTabLongestFlight,
            generalTabRevenueSixMonth, generalTabLeastPopularMeal, bookingsTabPricePaidLabel, bookingsTabCheckedBaggageLabel,
            bookingsTabCabinBaggageLabel;

    @FXML
    Button updateStatisticsButton;

    @FXML
    ComboBox<Integer> mDashFlightDropdown, mDashBookingDropdown;

    public ManagementDashboardController(
            FlightManager flightManager,
            BookingManager bookingManager,
            FlightRouteManager flightRouteManager,
            TicketManager ticketManager,
            MealTypeManager mealTypeManager,
            PersonManager personManager,
            AirportManager airportManager,
            AirplaneManager airplaneManager
    ) {
        this.flightManager = flightManager;
        this.bookingManager = bookingManager;
        this.flightRouteManager = flightRouteManager;
        this.ticketManager = ticketManager;
        this.mealTypeManager = mealTypeManager;
        this.personManager = personManager;
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
    }

    //////////////////////////////////// General Tab ////////////////////////////////////////////////////////////////

    /**
     * Helper method to use as an action which ensures that all the necessary KPI's computed from values from
     * the database are displayed inside the grid pane
     */
    @FXML
    private void updateFlightStatistics() {
        var lFlight = flightManager.getLongestFlight();
        var lDur = calcEST(lFlight.getDepartureTime(), lFlight.getArrivalTime());

        totalFlightsLabel.setText(String.valueOf(flightManager.getFlights().size()));
        totalRoutesLabel.setText(String.valueOf(flightRouteManager.getFlightRoutes().size()));
        totalBookingsLabel.setText(String.valueOf(bookingManager.getBookings().size()));
        totalTicketsLabel.setText(String.valueOf(ticketManager.getTickets().size()));
        totalRevenueLabel.setText(sumOfTicketPrices() + " €");
        popularMealLabel.setText(getMostBookedMeal().getMealName() + ", ordered " + getAmountOfPopularMeal() + " times");

        generalTabTotalAirports.setText(String.valueOf(airportManager.getAirports().size()));
        generalTabPopularAirport.setText(airportManager.mostPopularAirport().getFullName());
        generalTabMostCapacity.setText(airplaneManager.getBiggestPlane().getModel() +
                " with " + airplaneManager.getBiggestPlane().getCapacity() + " seats");
        generalTabLongestFlight.setText("Flight " + lFlight.getFlightID() + " going from " +
                lFlight.getOriginAirport() + " to " + lFlight.getDestinationAirport() +
                " for " + lDur +" minutes/" + lDur/60 + " hours");
        generalTabRevenueSixMonth.setText(String.valueOf(ticketManager.getSumOfTicketsOfLastSixMonth()));
        generalTabLeastPopularMeal.setText(getLeastBookedMeal().getMealName() + ", ordered " + getAmountOfLeastPopularMeal() + " times");

    }

    /**
     * Helper method to calculate the sum of all the tickets currently sold
     * @return the value of the sum as a BigDecimal
     */
    public BigDecimal sumOfTicketPrices() {
        return ticketManager.getSumOfTicketPrices(ticketManager.getTickets());
    }

    public MealType getMostBookedMeal() {
        return mealTypeManager.getMostBookedMeal();
    }

    public MealType getLeastBookedMeal() {
        return mealTypeManager.getLeastBookedMeal();
    }

    public int getAmountOfPopularMeal() {
        return mealTypeManager.getAmountOfMeals(getMostBookedMeal().getId());
    }

    public int getAmountOfLeastPopularMeal() {
        return mealTypeManager.getAmountOfMeals(getLeastBookedMeal().getId());
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
        if (mDashFlightDropdown.getValue() == null && mDashBookingDropdown.getValue() == null) {
            showAlert("Information", "Please select at least a flight first!", AlertType.INFORMATION);
        }
        else if (mDashBookingDropdown.getValue() == null) {
            var selectedFlightID = mDashFlightDropdown.getValue();
            var selectedFlightAsObject = flightManager.getFlights().stream()
                    .filter(f -> f.getFlightID() == selectedFlightID).findFirst().get();
            mDashDepLabel.setText(selectedFlightAsObject.getOriginAirport());
            mDashArrLabel.setText(selectedFlightAsObject.getDestinationAirport());
            mDashEstFDLabel.setText(calcEST(selectedFlightAsObject.getDepartureTime(), selectedFlightAsObject.getArrivalTime()) + " minutes");
            mDashBookingsLabel.setText(String.valueOf(bookingManager.getBookingsOfFlight(selectedFlightID).size()));
            mDashMealsLabel.setText(String.valueOf(mealTypeManager.getBookedMealsForSpecificFlight(selectedFlightID)));
            //bookingsTabRevenueLabel.setText(ticketManager.getRevenueOfFlight(selectedFlightID) + " €");
        }
        else {
            var selectedFlightID = mDashFlightDropdown.getValue();
            var selectedFlightAsObject = flightManager.getFlights().stream()
                    .filter(f -> f.getFlightID() == selectedFlightID).findFirst().get();
            var selectedBookingID = mDashBookingDropdown.getValue();
            var selectedBookingAsObject = bookingManager.getBookingsOfFlight(selectedFlightID).stream()
                    .filter(b -> b.getBookingId() == selectedBookingID).findFirst().get();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            var currentTickets = ticketManager.getTicketsOfBooking(selectedBookingID);

            mDashDepLabel.setText(selectedFlightAsObject.getOriginAirport());
            mDashArrLabel.setText(selectedFlightAsObject.getDestinationAirport());
            mDashEstFDLabel.setText(calcEST(selectedFlightAsObject.getDepartureTime(), selectedFlightAsObject.getArrivalTime()) + " minutes");
            mDashBookingsLabel.setText(String.valueOf(bookingManager.getBookingsOfFlight(selectedFlightID).size()));
            mDashMealsLabel.setText(String.valueOf(mealTypeManager.getBookedMealsForSpecificFlight(mDashFlightDropdown.getValue())));

            mDashBookedByLabel.setText(personManager.getPersonBooked(selectedBookingID).getLastName() +
                    ", " + personManager.getPersonBooked(selectedBookingID).getFirstName());

            bookingTabBookedOnLabel.setText(dtf.format(selectedBookingAsObject.getTimeOfBooking()));

            bookingsTabPricePaidLabel.setText(
                    String.valueOf(ticketManager.getSumOfTicketPrices(ticketManager.getTicketsOfBooking(selectedBookingID))));

            bookingsTabCheckedBaggageLabel.setText(String.valueOf(ticketManager.getCabinBaggageAmount(currentTickets)));

            bookingsTabCabinBaggageLabel.setText(String.valueOf(ticketManager.getCabinBaggageAmount(currentTickets)));

        }


    }

    public long calcEST(LocalDateTime start, LocalDateTime end) {
        return flightManager.calcEST(start, end);
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

        mDashBookedByLabel.setText("");
        bookingsTabCheckedBaggageLabel.setText("");
        bookingsTabCabinBaggageLabel.setText("");
        bookingsTabPricePaidLabel.setText("");
        bookingTabBookedOnLabel.setText("");
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
}
