package frontend;

import businessentitiesapi.*;
import businesslogic.bulkvalidator.BulkException;
import businesslogic.validators.PassengerInfoValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.homberghp.fxuiscraper.FXUIScraper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class PassengerInfoController {

    @FXML
    public GridPane passengerInfo;

    private Flight flight;
    private final FlightSeatManager flightSeatManager;
    private final PersonManager personManager;
    private final PassengerManager passengerManager;
    private final BookingManager bookingManager;
    private final TicketManager ticketManager;
    private final SeatManager seatManager;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public ComboBox<String> gender;

    @FXML
    public TextField email;

    @FXML
    public DatePicker birthday;

    @FXML
    public TextField passportNumber;

    @FXML
    public ComboBox<String> seatType;

    @FXML
    public ComboBox<Seat> seatNumber;

    @FXML
    public ComboBox<String> cabinLuggage;

    @FXML
    public ComboBox<String> handLuggage;

    @FXML
    public ComboBox<String> meal;

    //Look for all things inside the root pane
    FXUIScraper scraper = () -> passengerInfo;
    PassengerInfoValidator validator = new PassengerInfoValidator();
    private final Map<String, Integer> mealMap = Map.of(
            "None", 1,
            "Standard Meal", 2,
            "Vegan", 3,
            "Vegetarian", 4,
            "Muslim", 5,
            "Kosher", 6,
            "Child Meal", 7,
            "Diabetic", 8,
            "Gluten Free", 9,
            "Lactose Free", 10
    );

    public PassengerInfoController(FlightSeatManager flightSeatManager, PersonManager busine, PassengerManager passengerManager, BookingManager bookingManager, TicketManager ticketManager, SeatManager seatManager) {
        this.flightSeatManager = flightSeatManager;
        personManager = busine;
        this.passengerManager = passengerManager;
        this.bookingManager = bookingManager;
        this.ticketManager = ticketManager;
        this.seatManager = seatManager;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    void setPassengerFields(boolean isEnabled) {
        var actualState = !isEnabled;
        if (flight == null && isEnabled) {
            return;
        }
        passportNumber.setDisable(actualState);
        seatType.setDisable(actualState);
        cabinLuggage.setDisable(actualState);
        handLuggage.setDisable(actualState);
        meal.setDisable(actualState);
    }

    void submitInput() {
        var userInput = scraper.getKeyValues(x -> true);
        System.out.println();

        Map<String, Object> validatedInput;
        try {
            validatedInput = validator.validateInput(userInput);
        } catch (BulkException e) {
            var causes = e.getCauses();
            causes.values().forEach(s -> System.out.println(s.getMessage()));
            return;
        }

        var firstName = (String) validatedInput.get("firstName");
        var lastName = (String) validatedInput.get("lastName");
        var email = (String) validatedInput.get("email");
        var birthday = (LocalDate) validatedInput.get("birthday");
        var gender = ((String) validatedInput.get("gender")).substring(0,1).toLowerCase(Locale.ROOT);

        var passportNumber = (String) validatedInput.get("passportNumber");


        var person = personManager.createPerson(firstName, lastName, gender, email, birthday);
        var savedPerson = personManager.add(person);

        var passenger = passengerManager.createPassenger(passportNumber, savedPerson.getPersonId());
        var savedPassenger = passengerManager.add(passenger);

        var booking = bookingManager.createBooking(savedPerson.getPersonId(), 5, LocalDateTime.now());
        var savedBooking = bookingManager.add(booking);

        var flightSeat = flightSeatManager.getFromSeatId(seatNumber.getValue().getSeatId());
        var caLuggage = Integer.parseInt(cabinLuggage.getValue());
        var haLuggage = Integer.parseInt(handLuggage.getValue());
        var ticket = ticketManager.createTicket(
                0,
                flightSeat.getFlightSeatId(),
                caLuggage,
                haLuggage,
                mealMap.get(meal.getSelectionModel().getSelectedItem()),
                savedBooking.getBookingId(),
                false,
                savedPassenger.getPassengerId(),
                flight.getBasePrice()
                );
        var savedTicket = ticketManager.add(ticket);
    }





    @FXML
    void initialize() {
        seatType.setOnAction(this::onSeatTypeSelected);
    }

    @FXML
    public void onSeatTypeSelected(ActionEvent actionEvent) {
        var selectedType = seatType.getSelectionModel().getSelectedItem();
        if ("select".equals(selectedType)) {
            return;
        }

        seatNumber.setDisable(false);
        var flightSeats = flightSeatManager.getAvailableFlightSeats(flight, selectedType);
        var seats = flightSeats.stream().map(seatManager::getSeatForFlightSeat)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        seatNumber.setItems(seats);
        System.out.println(seats);
    }
}
