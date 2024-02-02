package com.atomicaggies.gui.avionicsgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //
        // Choosing a port
        //
        SerialPort chosenPort = null;
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            System.out.println(port.getSystemPortName());
        }
        chosenPort = SerialPort.getCommPort("cu.SLAB_USBtoUART");
        // Check if the port is valid
        if (chosenPort != null) {
            // Configure and open the port
            chosenPort.setBaudRate(115200); // Adjust the baud rate as needed
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

        // Initialize your DataAcquisitionService with the chosen port
        TelemetryDataModel telemetryDataModel = new TelemetryDataModel();
        DataAcquisitionService dataAcquisitionService = new DataAcquisitionService(telemetryDataModel, chosenPort);




    }

    public static void main(String[] args) {
        launch();
    }
}