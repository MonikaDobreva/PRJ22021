module GUI_module {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires businessentities_api_module;
    requires transitive businesslogic_module;

    opens frontend to javafx.fxml;
    exports frontend;
}
