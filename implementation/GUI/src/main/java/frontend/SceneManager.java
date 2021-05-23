package frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author
 */
public class SceneManager {

    private final Scene scene;
    private final Callback<Class<?>, Object> controllerFactory;
    private final Map<String, Parent> views = new HashMap<>();

    public SceneManager(Callback<Class<?>, Object> controllerFactory, String initialView) {
        this.controllerFactory = controllerFactory;
        scene = new Scene(loadScene(initialView));
    }

    public final void changeScene(String view) {
        scene.setRoot(views.computeIfAbsent(view, this::loadScene));
    }

    private Parent loadScene(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApp.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        fxmlLoader.setResources(ResourceBundle.getBundle("frontend.editAisStrings"));
        try {
            return fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, "Unable to load fxml", ex);
           Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, "Unable to load fxml", ex.getCause());
            return createErrorPane(fxmlResource, ex);
        }
    }

    void displayOn(Stage stage, int width, int height) {
        stage.setScene(scene);
        stage.setIconified(true);
        stage.getIcons().add(new Image(GUIApp.class.getResourceAsStream( "AISLogo1.png" )));
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    void displayOn(Stage stage) {
        stage.setScene(scene);
        stage.setIconified(true);
        stage.getIcons().add(new Image(GUIApp.class.getResourceAsStream( "AISLogo1.png" )));
        stage.show();
    }
    
    Parent createErrorPane(URL fxmlResource, IOException ex) {
        var parent =  new VBox();
        var titleLabel = new Label("Unable to load fxml");
        titleLabel.setTextFill(Paint.valueOf("#FF0000"));
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), 32));
        parent.getChildren().add(titleLabel);
        
        var loader = GUIApp.class.getClassLoader();
        var loaderName = loader.getName();
        
        addRow(parent, "File", fxmlResource.toString());
        addRow(parent, "Loader name", loaderName);
        
        addRow(parent, "Cause class", ex.getCause().getClass().toString());
        addRow(parent, "Cause message", ex.getCause().getMessage());
        
        var stackTrace = Stream.of(ex.getStackTrace()).limit(10).map(st -> st.toString()).collect(Collectors.joining("\n"));
        var stLabel = new Label("Stacktrace:");
        stLabel.setStyle("-fx-font-weight: bold;");
        parent.getChildren().add(stLabel);
        parent.getChildren().add(new TextArea(stackTrace));
        return parent;
    }
    
    void addRow(VBox parent, String label, String text){
        var row =  new HBox();
        
        var nameLabel = new Label(label + ": ");
        nameLabel.setStyle("-fx-font-weight: bold;");
        
        var textLabel = new Label(text);
        textLabel.setWrapText(true);
        
        row.getChildren().addAll(
                nameLabel,
                textLabel
        );
        
        parent.getChildren().add(row);
    }

}
