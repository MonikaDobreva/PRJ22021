package frontend;

//import businessentitiesapi.*;
import businessentitiesapi.*;
import businesslogic.BusinessLogicImplementationProvider;
import businesslogic.EditDetailsLogic;
import businesslogic.BusinessLogicAPI;
import static frontend.UIHelpers.printChildren;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import persistence.PersistenceAPI;
import persistence.PersistenceImplementationProvider;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import static org.mockito.Mockito.mock;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */
@ExtendWith( ApplicationExtension.class )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class CreateFlightGUITest {

    static {
        if ( Boolean.getBoolean( "SERVER" ) ) {
            System.setProperty( "java.awt.headless", "true" );
            System.setProperty( "testfx.robot", "glass" );
            System.setProperty( "testfx.headless", "true" );
            System.setProperty( "prism.order", "sw" );
            System.setProperty( "prism.text", "t2k" );
            System.setProperty( "glass.platform", "Monocle" );
            System.setProperty( "monocle.platform", "Headless" );
        }
    }

    CreateFlightLogic createFlightLogic;
    Stage stage;

    @Start
    void start( Stage stage ) throws IOException {
        this.stage = stage;
        createFlightLogic = mock(CreateFlightLogic.class);

        PersistenceAPI persistenceAPI = PersistenceImplementationProvider.getImplementation();
//        var ds = genericdao.pgdao.PGJDBCUtils.getDataSource("postgres");
        BusinessLogicAPI businessLogicAPI =
                BusinessLogicImplementationProvider.getImplementation(persistenceAPI,null);

        new GUIApp( businessLogicAPI ).init( false ).start( stage );
    }

    @Test
    public void createFlightTest( FxRobot robot ) {
        System.out.println( "stage = " + stage );
        Scene scene = stage.getScene();
        System.out.println( "scene = " + scene );
        Parent root = scene.getRoot();
        System.out.println( "root = " + root );
        printChildren( root );
        robot.clickOn( "#userChoice" )
                .type( KeyCode.DOWN )
                .type( KeyCode.ENTER )
                .clickOn( "#startBtn" )
                .clickOn( "#createFlightBtn" )
                .clickOn( "#depTimeHourSpinner" )
                .write( "10" )
                .clickOn( "#depTimeMinSpinner" )
                .write( "30" )
                .clickOn( "#depDatePicker" )
                .write( "12/07/2021" )
                .clickOn( "#arrTimeHourSpinner" )
                .write( "14" )
                .clickOn( "#arrTimeMinSpinner" )
                .write( "45" )
                .clickOn( "#arrDatePicker" )
                .write( "12/07/2021" );
//        assertSoftly( softly ->{
//            softly.assertThat()
//        });
    }

//        Mockito.when(apm.getAirplane("X-WXYZ")).thenReturn(CreateLogicHelper.a4);
//        Mockito.when(apsm.checkAvailability("X-WXYZ", depTime, arrTime)).thenReturn(true);
//        Mockito.when(fm.createFlight(0,
//                "AMS", "DUS",
//                LocalDateTime.of(2022, 10, 10, 10, 30),
//                LocalDateTime.of(2022, 10, 11, 12, 45),
//                "X-WXYZ",
//                new BigDecimal("120.00"))).thenReturn(CreateLogicHelper.f);
//        Mockito.doThrow(new RuntimeException()).when(frm).checkExistence("AMS", "DUS");
//        Mockito.when(fm.add(CreateLogicHelper.f)).thenReturn(CreateLogicHelper.f);
//        Mockito.when(sm.getSeatIdsOfAirplane(CreateLogicHelper.a4)).thenReturn(CreateLogicHelper.getSeatsId());
//        Mockito.when(fsm.createFlightSeat(0, 0, true)).thenReturn(CreateLogicHelper.fs1);
//        Mockito.when(fsm.createFlightSeat(1, 0, true)).thenReturn(CreateLogicHelper.fs2);
//        Mockito.when(fsm.createFlightSeat(2, 0, true)).thenReturn(CreateLogicHelper.fs3);
//        Mockito.when(fsm.add(CreateLogicHelper.fs1)).thenReturn(CreateLogicHelper.fs1);
//        Mockito.when(fsm.add(CreateLogicHelper.fs2)).thenReturn(CreateLogicHelper.fs2);
//        Mockito.when(fsm.add(CreateLogicHelper.fs3)).thenReturn(CreateLogicHelper.fs3);

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