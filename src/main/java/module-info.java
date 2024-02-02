module com.atomicaggies.gui.avionicsgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires io.reactivex.rxjava3;
    requires eu.hansolo.tilesfx;
    requires com.fazecast.jSerialComm;

    opens com.atomicaggies.gui.avionicsgui to javafx.fxml;
    exports com.atomicaggies.gui.avionicsgui;
}