package frontend;

import businessentitiesapi.AirplaneManager;
import businessentitiesapi.AirportManager;
import businessentitiesapi.Flight;
import businessentitiesapi.FlightManager;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Rachel
 */
public class editFlightController {
    
    @FXML
    Button displayFlights;
    
    @FXML
    TableView<Flight> flightsTable = new TableView<>();
    
    @FXML
    TableColumn<Flight, Integer> flightIdColumn = new TableColumn<>(), basePriceColumn = new TableColumn<>();
    
    @FXML
    TableColumn<Flight, String> originAirportColumn = new TableColumn<>(),
            destinationAirportColumn = new TableColumn<>(),
            airplaneColumn = new TableColumn<>();
    
    @FXML
    TableColumn<Flight, LocalDateTime> departureTimeColumn = new TableColumn<>(),
            arrivalTimeColumn = new TableColumn<>();
    /**
     * helper variable to store the clicked row.
     *
     * for the method editColoum and deleteFlight
     */
    private Flight clickedFlight;
    private Supplier<SceneManager> sceneManagerSupplier;
    private final FlightManager flightManager;
    private final AirportManager airportManager;
    private final AirplaneManager airplaneManager;
    
    public editFlightController(Supplier<SceneManager> sceneManagerSupplier, FlightManager flightManager, AirportManager airportManager, AirplaneManager airplaneManager) {
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.flightManager = flightManager;
        this.airportManager = airportManager;
        this.airplaneManager = airplaneManager;
    }
    
    public void initWindow(){
        showFlights();
    }
    
    @FXML
    private void showFlights() {
        clearFlightsView();
        var flights = flightManager.getFlights();
        flightsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        flightsTable.getItems().addAll(flights);
        flightIdColumn.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        originAirportColumn.setCellValueFactory(new PropertyValueFactory<>("originAirport"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        airplaneColumn.setCellValueFactory(new PropertyValueFactory<>("airplane"));
        basePriceColumn.setCellValueFactory(new PropertyValueFactory<>("basePrice"));
    }
    
    private void clearFlightsView() {
        flightsTable.getItems().clear();
    }

    /**
     * helper variable to store the last clicked time to check for double click.
     *
     * for the method editColoum
     */
    private LocalDateTime lastClicked;

    /**
     * This is the method called if a user clicks on a row in the tableView from
     * viewFlights.
     *
     * If the user double clicks a row which is not empty then it opens the edit
     * window with the information of the row already filled.
     */
    @FXML
    private void editColoum(MouseEvent event) {
        Flight selectedFlight = flightsTable.getSelectionModel().getSelectedItem();
        if (selectedFlight == null) {
            return;
        }
        if (selectedFlight != clickedFlight) {
            clickedFlight = selectedFlight;
            lastClicked = LocalDateTime.now();
        } else if (selectedFlight == clickedFlight) {
            LocalDateTime now = LocalDateTime.now();
            long between = lastClicked.until(now, ChronoUnit.MILLIS);
            if (between < 200) {
                //do something if the user has clicked twice in the distanz of 200ms

                Consumer<editDetailsFlightController> cont = (editDetailsFlightController c) -> c.setFlight(clickedFlight);
                sceneManagerSupplier.get().changeScene("editDetailsFlights", cont);
            } else {
                lastClicked = LocalDateTime.now();
            }
        }
    }
//    /**
//     * This is the method called if a user clicks on a row in the tableView from
//     * viewFlights.
//     *
//     * If the user double clicks a row which is not empty then it opens the edit
//     * window with the information of the row already filled.
//     */
//    @FXML
//    private void editColoum() {
//        Flight selectedItem = flightsTable.getSelectionModel().getSelectedItem();
//        if (selectedItem == null) {
//            return;
//        }
//        if (selectedItem != clickedFlight) {
//            clickedFlight = selectedItem;
//            lastClicked = LocalDateTime.now();
//        }else if(selectedItem==clickedFlight){
//            LocalDateTime now = LocalDateTime.now();
//            long between = lastClicked.until(now,ChronoUnit.MILLIS);
//            if (between<200) {
//                //do something if the user has clicked twice in the distanz of 200ms
////                sceneManagerSupplier.get().changeScene(view);
//                
//            }else{
//               lastClicked = LocalDateTime.now(); 
//            }
//        }
//    }
    
}
