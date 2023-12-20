package com.atomicaggies.gui.avionicsgui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import eu.hansolo.tilesfx.Tile;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class DashboardController {
    @FXML
    private Label welcomeText;  // FIXME temporary visual

    private CompositeDisposable disposables = new CompositeDisposable();
    private TelemetryDataModel telemetryDataModel = new TelemetryDataModel();
    private Tile temperatureTile; // Add UI element for temperature
    private Tile humidityTile;    // Add UI element for humidity

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }   //FIXME remove button


    @FXML
    public void initialize() {
        // Initialize Tiles and bind them to model
        // ...
    }

    // Add methods for binding and updating tiles
    // ...
}