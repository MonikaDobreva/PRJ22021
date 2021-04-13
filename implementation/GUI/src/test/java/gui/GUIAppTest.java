//package gui;
//
//import businessentitiesapi.*;
//import frontend.FlightController;
//import businesslogic.BusinessLogicAPI;
//
//import java.io.IOException;
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
//
//    @Start
//    void start(Stage stage) throws IOException {
//
//        flightManager = mock(FlightManager.class);
//        BusinessLogicAPI businessLogicAPI = () -> airportManager;
//        new GUIApp(businessLogicAPI).init(false).start(stage);
//    }
//
//    @Test
//    void testAddCustomer(FxRobot robot) {
//
//        when(flightManager.createFlight(anyString(), any(), any(), anyString(), anyString(), anyString()))
//                .thenReturn(new Flight() {
//
//                    @Override
//                    public String getName() {
//                        return "LH-388";
//                    }
//
//                    @Override
//                    public LocalDateTime getDepartureTime() {
//                        return LocalDateTime.parse("2020-01-01");
//                    }
//
//                    @Override
//                    public LocalDateTime getArrivalTime() {
//                        return LocalDateTime.parse("2020-01-02");
//                    }
//
//                    @Override
//                    public String getAirplane() {
//                        return "Boeing 747";
//                    }
//
//                    @Override
//                    public String getStartAirport() {
//                        return "DUS";
//                    }
//
//                    @Override
//                    public String getDestAirport() {
//                        return "ATX";
//                    }
//
//                });
//
//        ArgumentCaptor<Flight> flightArgumentCaptor = ArgumentCaptor.forClass(Flight.class);
//
//        robot
//                .clickOn("#flightName")
//                .write("LH-388")
//                .clickOn("#depTime")
//                .write("14:45")
//                .clickOn("#arrTime")
//                .write("15:34")
//                .clickOn("airplane")
//                .write("Boeing 747")
//                .clickOn("startAirport")
//                .write("DUS")
//                .clickOn("destAirport")
//                .write("ATX");
//
//        verify(flightManager).add(flightArgumentCaptor.capture());
//
//        assertSoftly(softly -> {
//            softly.assertThat(flightArgumentCaptor.getValue().getName()).isEqualTo("LH-388");
//            softly.assertThat(flightArgumentCaptor.getValue().getDepartureTime().toString()).isEqualTo("2020-01-01T14:45");
//            softly.assertThat(flightArgumentCaptor.getValue().getDepartureTime().toString()).isEqualTo("2020-01-02T15:34");
//            softly.assertThat(flightArgumentCaptor.getValue().getName()).isEqualTo("Boeing 747");
//            softly.assertThat(flightArgumentCaptor.getValue().getName()).isEqualTo("DUS");
//            softly.assertThat(flightArgumentCaptor.getValue().getName()).isEqualTo("ATX");
//        });
//    }
//}
