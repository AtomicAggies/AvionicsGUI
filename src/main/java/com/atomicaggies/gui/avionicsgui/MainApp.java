package com.atomicaggies.gui.avionicsgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;

public class MainApp extends Application {
    private final int BAUD_RATE = 115200;
    private final String PORT_NAME = "cu.SLAB_USBtoUART";
    @Override
    public void start(Stage stage) throws IOException {

        //
        // Setting the stage and Scene
        //
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("dashboard-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Dashboard Example");
        stage.setScene(scene);
        stage.show();

        //
        //
        //

        //
        // Choosing a port
        //
        System.out.println("Listing Ports");
        SerialPort chosenPort = null;
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            System.out.println(port.getSystemPortName());
        }
        chosenPort = SerialPort.getCommPort(PORT_NAME);
        // Check if the port is valid
        if (chosenPort != null) {
            // Configure and open the port
            chosenPort.setBaudRate(BAUD_RATE); // Adjust the baud rate as needed
            if (chosenPort.openPort()) {
                System.out.println("Port opened successfully.");
            } else {
                System.out.println("Failed to open port.");
                return; // Exit if we can't open the port
            }
        } else {
            System.out.println("Port not found.");
            return; // Exit if the specified port is not found
        }
        //
        // Port is Chosen at this point
        //

        //telemetryDataModel
        TelemetryDataModel telemetryDataModel = new TelemetryDataModel();
        // Get the controller and set the TelemetryDataModel
        DashboardController controller = fxmlLoader.getController();
        controller.setTelemetryDataModel(telemetryDataModel);

        // Initialize your DataAcquisitionService with the chosen port

        DataAcquisitionService dataAcquisitionService = new DataAcquisitionService(telemetryDataModel, chosenPort);
        dataAcquisitionService.startReading();

    }

    public static void main(String[] args) {
        launch();
    }
}