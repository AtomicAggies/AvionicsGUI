module com.atomicaggies.gui.avionicsgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.atomicaggies.gui.avionicsgui to javafx.fxml;
    exports com.atomicaggies.gui.avionicsgui;
}