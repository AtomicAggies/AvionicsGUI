package com.atomicaggies.gui.avionicsgui;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;

public class DataAcquisitionService extends Thread{
    private TelemetryDataModel telemetryDataModel;
    private SerialPort serialPort;

    public DataAcquisitionService(TelemetryDataModel telemetryDataModel, SerialPort port) {
        this.telemetryDataModel = telemetryDataModel;
        this.serialPort = port;
    }

    //PRE: rawData with prefix such as GSDATA: "GSDATA" + "data"+...
    //POST: returns the appropriate TelemetrySnapshotCreator
    public TelemetrySnapshotCreator getCreatorFor(String rawData) throws DataParsingException {
        if(rawData.startsWith("GSDATA")){
            return new FauxTelemetrySnapshotCreator();
        }
//        else if (rawData.startsWith("BMP")) {
//            return new BMPTelemetrySnapshotCreator();
//        } else if (rawData.startsWith("ADXL")) {
//            return new ADXLTelemetrySnapshotCreator();
//        }
        else {
            throw new DataParsingException("Unknown data type");
        }
    }


    //PRE: none
    //POST: Updates tiles data while serialPort isOpen
    public void run() {

        while (serialPort.isOpen()) {
            if (serialPort.bytesAvailable() > 0) {
                byte[] readBuffer = new byte[serialPort.bytesAvailable()];
                int numRead = serialPort.readBytes(readBuffer, readBuffer.length);

                // Process the read data (this is just an example, adjust according to your data format)
                String receivedData = new String(readBuffer, 0, numRead);
                System.out.println("Received raw data: " + receivedData);

                //create Appropriate Factory, create Snapshot, Update TelemetryDataModel
                Platform.runLater(()->{
                    TelemetrySnapshotCreator creator;
                    TelemetrySnapshot snapshot;
                    try {
                         creator= getCreatorFor(receivedData);
                         snapshot= creator.createProduct(receivedData);
                    } catch (DataParsingException e) { //FIXME print the raw data somewhere on the GUI so its visible
                        throw new RuntimeException(e);
                    }
                    snapshot.updateModel(telemetryDataModel);
                });

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

