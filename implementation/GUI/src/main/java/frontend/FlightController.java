package frontend;

import businessentitiesapi.Flight;
import businesslogic.BusinessLogicAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    private BusinessLogicAPI businessLogicAPI;

    public FlightController(){

    }

    public FlightController(BusinessLogicAPI logicAPI){
        this.businessLogicAPI = logicAPI;
    }

    @FXML
    private void switchToSecondary() throws IOException{
        GUIApp.setRoot("secondary");
    }

    @FXML
    private void storeFlight(){
        Flight f = businessLogicAPI.getFlightManager().createFlight(
                flightName.getText(),
                LocalDate.parse(depTime.getText()),
                LocalDate.parse(arrTime.getText())
        );
        businessLogicAPI.getFlightManager().add(f);
    }


    public List<Flight> showFlights() {
        return businessLogicAPI.getFlightManager().getFlights();
    }
}
