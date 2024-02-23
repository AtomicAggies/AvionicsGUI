package com.atomicaggies.gui.avionicsgui;


import eu.hansolo.tilesfx.TileBuilder;
import io.reactivex.rxjava3.disposables.Disposable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import eu.hansolo.tilesfx.Tile;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class DashboardController {
    @FXML
    private Pane tileContainer; // This Pane will act as a placeholder for TilesFX components //FIXME USE Factory Method for cleaner code

    //UI ELEMENTS
    private CompositeDisposable disposables = new CompositeDisposable();
    private TelemetryDataModel telemetryDataModel;
    private Tile temperatureTile; // Add UI element for temperature
    private Tile humidityTile;    // Add UI element for humidity

    public void setTelemetryDataModel(TelemetryDataModel telemetryDataModel) {
        this.telemetryDataModel = telemetryDataModel;
        initializeSubscriptions();
    }

    @FXML
    public void initialize() {
        // Setup UI components that don't depend on the telemetryDataModel
    }

    //PRE: Ensure telemetryDataModel is already set/injected before calling.
    //      In other words, scene.show() already called
    //POST: Initialize Tiles and bind them to model
    public void initializeSubscriptions() {
        temperatureTile =TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .title("Temperature")
                .unit("°C")
                .build();

        tileContainer.getChildren().add(temperatureTile);
        Disposable tempSub = telemetryDataModel.getTemperatureObservable().subscribe(this::updateTemperatureTile);
        disposables.add(tempSub);

        // Additional subscription setup can go here
    }

    // PRE: receives a temperature
    //POST: updates the temperature tile
    private void updateTemperatureTile(double temperature) {
        // Called by RxJava subscription to update the UI
        System.out.println("updating the temperature tile");
        Platform.runLater(() -> temperatureTile.setValue(temperature));
    }


    //FIXME should override stop method in MainApp via atOverride
    //FIXME create a func to dispose in Controller class
    // Also, closing the window should shut down the background thread.
//    public void disposeDisposables() {
//        if (!disposables.isDisposed()) {
//            disposables.dispose();
//        }
//    }
//    public void stop() throws Exception {
////        super.stop(); FIXME
//        System.out.println("Disposing of disposables");
//        disposables.dispose(); // Dispose all subscriptions
//    }
    // Add methods for binding and updating tiles
    // ...
}