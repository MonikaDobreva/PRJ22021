//package gui;
//
//import businessentitiesapi.*;
//import businesslogic.EditDetailsLogic;
//import frontend.FlightController;
//import businesslogic.BusinessLogicAPI;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//
//import frontend.GUIApp;
//import javafx.stage.Stage;
//
//import static org.assertj.core.api.SoftAssertions.assertSoftly;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.testfx.api.FxRobot;
//import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.framework.junit5.Start;
//
//import java.io.IOException;
//
//import static org.mockito.Mockito.mock;
//
///**
// * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
// */
//
//@ExtendWith(ApplicationExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class GUIAppTest {
//
//    static {
//        if (Boolean.getBoolean("SERVER")) {
//            System.setProperty("java.awt.headless", "true");
//            System.setProperty("testfx.robot", "glass");
//            System.setProperty("testfx.headless", "true");
//            System.setProperty("prism.order", "sw");
//            System.setProperty("prism.text", "t2k");
//            System.setProperty("glass.platform", "Monocle");
//            System.setProperty("monocle.platform", "Headless");
//        }
//    }
//
//    FlightManager flightManager;
//    AirportManager airportManager;
//    AirplaneManager airplaneManager;
//    FlightRouteManager flightRouteManager;
//    BookingManager bookingManager;
//    EditDetailsLogic editDetailsLogic;
//
//    @Start
//    void start(Stage stage) throws IOException {
//
//        flightManager = mock(FlightManager.class);
//        airportManager = mock(AirportManager.class);
//        airplaneManager = mock(AirplaneManager.class);
//        bookingManager = mock(BookingManager.class);
//        editDetailsLogic = mock(EditDetailsLogic.class);
//        flightRouteManager = mock(FlightRouteManager.class);
//
//        BusinessLogicAPI businessLogicAPI = () ->  {
//            airportManager, airplaneManager, flightManager, flightRouteManager, bookingManager, editDetailsLogic;
//        };
//        new GUIApp(businessLogicAPI).init(false).start(stage);
//    }
//
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
//}
