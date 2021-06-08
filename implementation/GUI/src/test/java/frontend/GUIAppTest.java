package frontend;

import businessentitiesapi.*;
import businesslogic.BusinessLogicImplementationProvider;
import businesslogic.EditDetailsLogic;
import businesslogic.BusinessLogicAPI;

import javafx.stage.Stage;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.assertj.core.api.SoftAssertions;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import persistence.PersistenceAPI;
import persistence.PersistenceImplementationProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
//import static javafx.beans.binding.Bindings.when;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */
@ExtendWith( ApplicationExtension.class )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class GUIAppTest {

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

    FlightManager flightManager;
    AirportManager airportManager;
    AirplaneManager airplaneManager;
    FlightRouteManager flightRouteManager;
    BookingManager bookingManager;
    EditDetailsLogic editDetailsLogic;

    @Start
    void start( Stage stage ) throws IOException {

        flightManager = mock( FlightManager.class );
        airportManager = mock( AirportManager.class );
        airplaneManager = mock( AirplaneManager.class );
        bookingManager = mock( BookingManager.class );
        editDetailsLogic = mock( EditDetailsLogic.class );
        flightRouteManager = mock( FlightRouteManager.class );

        PersistenceAPI persistenceAPI = PersistenceImplementationProvider.getImplementation();
        var ds = genericdao.pgdao.PGJDBCUtils.getDataSource( "postgres" );
        BusinessLogicAPI businessLogicAPI
                = BusinessLogicImplementationProvider.getImplementation( persistenceAPI, new genericdao.pgdao.PGDAOFactory( ds ) );
        new GUIApp( businessLogicAPI ).init( false ).start( stage );
    }

    @Test
    public void init( FxRobot robot ) {
//        robot
//                .clickOn("#userChoice");
//        assertSoftly( softly ->{
//            softly.assertThat()
//        });
    }

    Flight sampleFlight = new Flight(
            1,
            "DUS",
            "AUS",
            LocalDateTime.parse( "2022-01-01T10:10:00" ),
            LocalDateTime.parse( "2022-01-01T13:10:00" ),
            "K/ABDC",
            BigDecimal.valueOf( 150 ) );

    @Test
    void testAddCustomer() {

        when( flightManager.createFlight(
                sampleFlight.getFlightID(),
                sampleFlight.getOriginAirport(),
                sampleFlight.getDestinationAirport(),
                sampleFlight.getDepartureTime(),
                sampleFlight.getArrivalTime(),
                sampleFlight.getAirplane(),
                sampleFlight.getBasePrice() ) )
                .thenReturn( sampleFlight );

        ArgumentCaptor<Flight> flightArgumentCaptor = ArgumentCaptor.forClass( Flight.class );
        FxRobot robot = new FxRobot();
        robot
                .clickOn( "#depTimeHourSpinner" )
                .write( "10" )
                .clickOn( "#depTimeMinSpinner" )
                .write( "10" )
                .clickOn( "#depDatePicker" )
                .write( "2022-01-01" )
                .clickOn( "arrTimeHourSpinner" )
                .write( "13" )
                .clickOn( "arrTimeMinSpinner" )
                .write( "10" )
                .clickOn( "arrDatePicker" )
                .write( "2022-01-01" )
                .clickOn( "#originApDropdown" )
                .clickOn( "DUS" )
                .clickOn( "#destinationApDropdown" )
                .clickOn( "AUS" )
                .clickOn( "#airplaneModelDropdown" )
                .clickOn( "Boeing 747" )
                .clickOn( "#basePriceField" )
                .write( "100" )
                .clickOn( "#storeFlightsButton" );

        verify( flightManager ).add( flightArgumentCaptor.capture() );

        SoftAssertions.assertSoftly( softly -> {
            softly.assertThat( flightArgumentCaptor.getValue().getFlightID() ).isEqualTo( "1" );
            softly.assertThat( flightArgumentCaptor.getValue().getOriginAirport() ).isEqualTo( "DUS" );
            softly.assertThat( flightArgumentCaptor.getValue().getDestinationAirport() ).isEqualTo( "AUS" );
            softly.assertThat( flightArgumentCaptor.getValue().getDepartureTime().toString() ).isEqualTo( "2022-01-01T10:10:00" );
            softly.assertThat( flightArgumentCaptor.getValue().getArrivalTime().toString() ).isEqualTo( "2022-01-01T13:10:00" );
//            softly.assertThat( flightArgumentCaptor.getValue().getAirplaneModel() ).isEqualTo( "Boeing 747" );
            softly.assertThat( flightArgumentCaptor.getValue().getBasePrice().toString() ).isEqualTo( "100" );
        } );
    }
}
