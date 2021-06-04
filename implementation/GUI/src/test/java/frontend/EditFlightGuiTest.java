package frontend;

import businessentitiesapi.*;
import frontend.FlightController;
import businesslogic.BusinessLogicAPI;
import businesslogic.BusinessLogicAPIImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import frontend.GUIApp;
import javafx.stage.Stage;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

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

import java.io.IOException;

import static org.mockito.Mockito.mock;

/**
 *
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
    
//
//   @Start
//    void start(Stage stage) throws IOException {
//         flightManager = mock(FlightManager.class);
//        BusinessLogicAPI businessLogicAPI = new BusinessLogicAPIImpl();
//        new GUIApp(businessLogicAPI).init(false).start(stage);
//
//     
//    }

//    //@Disabled("Think TDD")
//    @Test
//    void testAddCustomer(FxRobot robot) {
//
//        when(customerManager.createCustomer(anyString(), any()))
//                .thenReturn(new Customer(0, "Elon Musk", LocalDate.of(1971, Month.JUNE, 28)));
//
//        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
//
//        robot
//                .clickOn("#customerName")
//                .write("Elon Musk")
//                .clickOn("#dateOfBirth")
//                .write("1971-06-28")
//                .clickOn("#storeCustomer");
//
//        verify(customerManager).add(customerCaptor.capture());
//
//        assertSoftly( softly -> {
//            softly.assertThat(customerCaptor.getValue().getName()).isEqualTo("Elon Musk");
//            softly.assertThat(customerCaptor.getValue().getDateOfBirth()).isEqualTo(LocalDate.of(1971, Month.JUNE, 28));
//        });
//    }
}
