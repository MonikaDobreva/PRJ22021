module GUI_module {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires businessentities_api_module;
    requires transitive businesslogic_module;
    requires java.logging;
    requires nl.homberghp.fxuiscraper;
    opens frontend to javafx.fxml;
    exports frontend;
}
