package frontend;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.util.Callback;
import businesslogic.BusinessLogicAPI;
import javafx.application.Platform;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class GUIApp extends Application {

    private BusinessLogicAPI businessLogicAPI;
    private SceneManager sceneManager;
    private static final String INITIAL_VIEW = "welcome";

    private final Callback<Class<?>, Object> controllerFactory = (Class<?> c)
            -> {

        switch (c.getName()) {
            case "frontend.FlightController":
                return new FlightController(this::getSceneManager, businessLogicAPI.getFlightManager(), businessLogicAPI.getAirportManager(),
                        businessLogicAPI.getAirplaneManager(), businessLogicAPI.getFlightRouteManager(), businessLogicAPI.getSeatManager() ,
                        businessLogicAPI.getFlightSeatManager());
            case "frontend.TopMenuController":
                return new TopMenuController(this::getSceneManager);
            case "frontend.WelcomeController":
                return new WelcomeController(this::getSceneManager);
            case "frontend.managementDashboardController":
                return new managementDashboardController(
                        businessLogicAPI.getFlightManager(),
                        businessLogicAPI.getAirportManager(),
                        businessLogicAPI.getAirplaneManager(),
                        businessLogicAPI.getBookingsManager(),
                        businessLogicAPI.getFlightRouteManager(),
                        businessLogicAPI.getTicketManager(),
                        businessLogicAPI.getMealTypeManager());
            case "frontend.editFlightController":
                return new editFlightController(this::getSceneManager, businessLogicAPI.getFlightManager(), businessLogicAPI.getAirportManager(),
                        businessLogicAPI.getAirplaneManager());
            case "frontend.editDetailsFlightController":
                return new editDetailsFlightController(this::getSceneManager,businessLogicAPI);
            case "frontend.SalesOfficerController":
                return new SalesOfficerController(this::getSceneManager);
            case "frontend.DeleteFlightController":
                return new DeleteFlightController(this::getSceneManager, businessLogicAPI.getFlightManager());
            case "frontend.CreateBookingController":
                return new CreateBookingController(this::getSceneManager, businessLogicAPI.getFlightManager());
            case "frontend.PassengerInfoController":
                return new PassengerInfoController(businessLogicAPI.getFlightSeatManager());

            default:
                return null;
        }
//        if(c.getName().equals("frontend.welcomeController")){
//            return new welcomeController();
//        }
//        if(c.getName().equals("frontend.FlightController")){
//            return new FlightController(businessLogicAPI);
//        }
//        if(c.getName().equals("frontend.SecondaryController")){
//            return new SecondaryController();
//        }
//        return null;
    };

    public GUIApp(BusinessLogicAPI businessLogicAPI) {
        this.businessLogicAPI = businessLogicAPI;
    }

    public GUIApp show() {
        return init(true);
    }

    public GUIApp init(boolean startJavaFXToolkit) {

        if (startJavaFXToolkit) {

            Platform.startup(() -> {
            });

            initializeSceneManager();

            Platform.runLater(() -> {
                Stage stage = new Stage();
                try {
                    start(stage);
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            });

        } else {
            initializeSceneManager();
        }

        return this;
    }

    private void initializeSceneManager() {
        sceneManager = new SceneManager(controllerFactory, INITIAL_VIEW);

    }

    @Override
    public void start(Stage stage) throws IOException {
        //sceneManager.displayOn(stage, 480, 550);
        sceneManager.displayOn(stage);

    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("welcome"), 482, 524);
//
////        Image image = new Image(new File("icons/send.png").toURI().toString());
////        stage.getIcons().add(appIcon);
//
//        stage.setTitle("AIS");
//        stage.setScene(scene);
//        stage.show();
//    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(GUIApp.class.getResource(fxml + ".fxml"));
//
//        fxmlLoader.setControllerFactory(controllerFactory);
//
//        return fxmlLoader.load();
//    }
//
//
//
//    public static void main(String[] args){
//        launch();
//    }
//
//    public void startFrontEnd(BusinessLogicAPI api){
//        businessLogicAPI = api;
//        launch();
//    }

}
