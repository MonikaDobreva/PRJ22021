package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Callback;

/**
 * JavaFX Switcher
 */
public class Switcher extends Application implements RootSwitcher {

    private static Scene scene;
    private final Map<String, Parent> sceneParents = new HashMap<>();
    private String currentRoot="primary";
    @Override
    public void start( Stage stage ) throws IOException {
        scene = new Scene( loadFXML( currentRoot ), 640, 480 );
        stage.setScene( scene );
        stage.show();
    }

    public String setRoot( String fxml ) {
        String prevRoot=currentRoot;
        currentRoot= fxml;
        scene.setRoot( loadFXML( currentRoot ) );
        return prevRoot;
    }

    private Parent loadFXML( String fxml ) {
        return sceneParents.computeIfAbsent( fxml, this::loadActualFXML );
    }

    private Parent loadActualFXML( final String s ) {
        Parent result = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader( Switcher.class.getResource( s + ".fxml" ) );
            Callback<Class<?>, Object> get = controllerFactories.get( s);
            fxmlLoader.setControllerFactory( controllerFactories.get( s) );
            result = fxmlLoader.load();
        } catch ( IOException ex ) {
            Logger.getLogger( Switcher.class.getName() ).log( Level.SEVERE, ex.getMessage() );
        }
        return result;
    }

    Map<String, Callback<Class<?>, Object>> controllerFactories
            = Map.ofEntries(
                    entry( "primary", (clz) -> new PrimaryController( this ) ),
                    entry( "secondary", (clz) -> new SecondaryController( this ) )
            );


    public static void main( String[] args ) {
        launch();
    }

}
