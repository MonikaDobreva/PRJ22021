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
                return new FlightController(this::getSceneManager, businessLogicAPI.getFlightManager());
            case "frontend.topMenuController":
                return new topMenuController(this::getSceneManager);
            case "frontend.welcomeController":
                return new welcomeController(this::getSceneManager);
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

    private void initializeSceneManager(){
        sceneManager = new SceneManager(controllerFactory, INITIAL_VIEW);

    }

    @Override
    public void start(Stage stage) throws IOException {
        sceneManager.displayOn(stage, 480, 550);

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
