package frontend;

import businessentitiesapi.*;
import businesslogic.BusinessLogicImplementationProvider;
import businesslogic.EditDetailsLogic;
import frontend.FlightController;
import businesslogic.BusinessLogicAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import frontend.GUIApp;
import javafx.stage.Stage;

import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import persistence.PersistenceAPI;
import persistence.PersistenceImplementationProvider;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static frontend.UIHelpers.printChildren;
import java.lang.ref.SoftReference;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;

import static org.mockito.Mockito.mock;

/**
 * @author Rachel
 */
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditFlightGuiTest {

    static {
        if (Boolean.getBoolean("SERVER")) {
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("glass.platform", "Monocle");
            System.setProperty("monocle.platform", "Headless");
        }
    }

    FlightManager flightManager;
    AirportManager airportManager;
    AirplaneManager airplaneManager;
    FlightRouteManager flightRouteManager;
    BookingManager bookingManager;
    EditDetailsLogic editDetailsLogic;
    Stage stage;

    @Start
    void start(Stage stage) throws IOException {
        this.stage = stage;
        flightManager = mock(FlightManager.class);
        airportManager = mock(AirportManager.class);
        airplaneManager = mock(AirplaneManager.class);
        bookingManager = mock(BookingManager.class);
        editDetailsLogic = mock(EditDetailsLogic.class);
        flightRouteManager = mock(FlightRouteManager.class);

        PersistenceAPI persistenceAPI = PersistenceImplementationProvider.getImplementation();
        BusinessLogicAPI businessLogicAPI = BusinessLogicImplementationProvider.getImplementation(persistenceAPI);

        new GUIApp(businessLogicAPI).init(false).start(stage);
    }

    @Test
    public void tinit() {
        SoftAssertions softly = new SoftAssertions();
        FxRobot robot = new FxRobot();
        System.out.println("stage = " + stage);
        Scene scene = stage.getScene();
        System.out.println("scene = " + scene);
        Parent root = scene.getRoot();
        System.out.println("root = " + root);
        printChildren(root);

     
        
//        final ComboBox b = (ComboBox) robot.lookup("#userChoice").query();
//        robot.interact(() -> {
//            b.getSelectionModel().select("Sales Officer");
//        });
////        String selectedItem = (String) b.getSelectionModel().getSelectedItem();
////        System.out.println("selected Items " + selectedItem);
////        Button button = (Button) robot.lookup( "#startBtn" ).query(); //<2>
////            robot.clickOn( button ); //<3> 
//        robot.clickOn("#startBtn");
////         selectedItem = (String) b.getSelectionModel().getSelectedItem();
////        System.out.println("selected Items " + selectedItem);
//        assertThat(stage.getScene().getRoot().getId()).isEqualTo("salesOfficerOptions");

//        ComboBox cb = (ComboBox) robot.lookup( "#userChoice" ).query();
////        cb.setValue("salesOfficerOptions");
//        robot.interact(()->{cb.setValue("salesOfficerOptions");});
//        robot.interact(()->{});
//       
//        System.out.println();
//        assertThat(stage.getScene().getRoot().getId()).isEqualTo("salesOfficerOptions");
//        robot.clickOn("#userChoice")
//                .type(KeyCode.DOWN)
//                .type(KeyCode.ENTER)
//                .clickOn("#startBtn");
//        System.out.println("change scene");
//        printChildren(stage.getScene().getRoot());
//        robot.clickOn("#editFlightBtn");
//        assertSoftly( softly ->{
//            softly.assertThat()
//        });
    }

//    @Test
//    void testAddCustomer(FxRobot robot) {
//
//        when(flightManager.createFlight(any(), anyString(), anyString(), LocalDateTime.parse(anyString()), LocalDateTime.parse(anyString()), anyString(), any()))
//                .thenReturn(new Flight(
//                        1,
//                        "DUS",
//                        "AUS",
//                        LocalDateTime.parse("2022-01-01T10:10:00"),
//                        LocalDateTime.parse("2022-01-01T13:10:00"),
//                        "Boeing 747",
//                        BigDecimal.valueOf(150)) {
//                });
//
//        ArgumentCaptor<Flight> flightArgumentCaptor = ArgumentCaptor.forClass(Flight.class);
//
//        robot
//                .clickOn("#depTimeHourSpinner")
//                .write("10")
//                .clickOn("#depTimeMinSpinner")
//                .write("10")
//                .clickOn("#depDatePicker")
//                .write("2022-01-01")
//                .clickOn("arrTimeHourSpinner")
//                .write("13")
//                .clickOn("arrTimeMinSpinner")
//                .write("10")
//                .clickOn("arrDatePicker")
//                .write("2022-01-01")
//                .clickOn("#originApDropdown")
//                .clickOn("DUS")
//                .clickOn("#destinationApDropdown")
//                .clickOn("AUS")
//                .clickOn("#airplaneModelDropdown")
//                .clickOn("Boeing 747")
//                .clickOn("#basePriceField")
//                .write("100")
//                .clickOn("#storeFlightsButton");
//
//        verify(flightManager).add(flightArgumentCaptor.capture());
//
//        assertSoftly(softly -> {
//            softly.assertThat(flightArgumentCaptor.getValue().getFlightID()).isEqualTo("1");
//            softly.assertThat(flightArgumentCaptor.getValue().getOriginAirport()).isEqualTo("DUS");
//            softly.assertThat(flightArgumentCaptor.getValue().getDestinationAirport()).isEqualTo("AUS");
//            softly.assertThat(flightArgumentCaptor.getValue().getDepartureTime().toString()).isEqualTo("2022-01-01T10:10:00");
//            softly.assertThat(flightArgumentCaptor.getValue().getArrivalTime().toString()).isEqualTo("2022-01-01T13:10:00");
//            softly.assertThat(flightArgumentCaptor.getValue().getAirplaneModel()).isEqualTo("Boeing 747");
//            softly.assertThat(flightArgumentCaptor.getValue().getBasePrice().toString()).isEqualTo("100");
//        });
//    }
}
