package com.atomicaggies.gui.avionicsgui;

import com.fazecast.jSerialComm.SerialPort;

public class DataAcquisitionService extends Thread{
    private TelemetryDataModel telemetryDataModel;
    private SerialPort serialPort;

    public DataAcquisitionService(TelemetryDataModel telemetryDataModel, SerialPort port) {
        this.telemetryDataModel = telemetryDataModel;
        this.serialPort = port;
    }

    public void run() {

        while (serialPort.isOpen()) {
            if (serialPort.bytesAvailable() > 0) {
                byte[] readBuffer = new byte[serialPort.bytesAvailable()];
                int numRead = serialPort.readBytes(readBuffer, readBuffer.length);

                // Process the read data (this is just an example, adjust according to your data format)
                String receivedData = new String(readBuffer, 0, numRead);
                System.out.println("Received data: " + receivedData);

                // Update the TelemetryDataModel (implement according to your data handling)
//                telemetryDataModel.updateTime(receivedData);
            }

            try {
                Thread.sleep(100); // Small delay to prevent tight looping
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        System.out.print("I am using port: ");
        System.out.println(serialPort.getSystemPortName());


    }

    // Method to start reading data
    public void startReading() {
        this.start(); // Start the thread
    }
}

