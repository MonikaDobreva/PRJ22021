package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.util.Callback;
import businesslogic.BusinessLogicAPI;

/**
 * @author Benjamin Swiezy {@code b.swiezy@student.fontys.nl}
 */

public class GUIApp extends Application {

    private static Scene scene;
    private static BusinessLogicAPI businessLogicAPI;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("flightView"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApp.class.getResource(fxml + ".fxml"));

        fxmlLoader.setControllerFactory(controllerFactory);

        return fxmlLoader.load();
    }

    private static final Callback<Class<?>, Object> controllerFactory = ( Class<?> c) -> {
        if(c.getName().equals("frontend.FlightController")){
            return new FlightController(businessLogicAPI);
        }
        if(c.getName().equals("frontend.SecondaryController")){
            return new SecondaryController();
        }
        return null;
    };

    public static void main(String[] args){
        launch();
    }

    public void startFrontEnd(BusinessLogicAPI api){
        businessLogicAPI = api;
        launch();
    }

}
