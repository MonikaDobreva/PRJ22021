package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class topMenuController {

    private final Supplier<SceneManager> sceneManagerSupplier;

    public topMenuController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }

    @FXML
    MenuItem newFlightMenu, newBookingMenu, newRouteMenu, logoutMenu, editFlightMenu, editBookingMenu,
            viewFlightsMenu, viewBookingsMenu, viewRoutesMenu, viewDashboardMenu, aboutMenu;

    @FXML
    MenuBar menuBar;

    @FXML
    private void switchToCreateFlight() throws IOException {
        sceneManagerSupplier.get().changeScene("createFlight");
    }

    @FXML
    private void switchToCreateBooking() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void switchToCreateRoute() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void logout() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void switchToEditFlight() throws IOException {
        sceneManagerSupplier.get().changeScene("editDetailsFlights");
    }

    @FXML
    private void switchToEditBooking() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void switchToViewFlights() throws IOException {
        sceneManagerSupplier.get().changeScene("viewFlights");
    }

    @FXML
    private void switchToViewBookings() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void switchToViewRoutes() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }

    @FXML
    private void switchToViewDashboard() throws IOException {
        sceneManagerSupplier.get().changeScene("managementDashboardView");
    }

    @FXML
    private void switchToAboutUs() throws IOException {
        sceneManagerSupplier.get().changeScene("welcome");
    }



}
