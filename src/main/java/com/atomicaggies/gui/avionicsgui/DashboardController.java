package com.atomicaggies.gui.avionicsgui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import eu.hansolo.tilesfx.Tile;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class DashboardController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}