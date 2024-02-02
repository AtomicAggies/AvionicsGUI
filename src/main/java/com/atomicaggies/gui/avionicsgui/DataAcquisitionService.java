package com.atomicaggies.gui.avionicsgui;

import com.fazecast.jSerialComm.SerialPort;

public class DataAcquisitionService {
    private TelemetryDataModel telemetryDataModel;
    SerialPort myPort;

    public DataAcquisitionService(TelemetryDataModel telemetryDataModel, SerialPort port) {
        this.telemetryDataModel = telemetryDataModel;
        this.myPort = port;
    }

    public void start() {


        System.out.print("I am using port: ");
        System.out.println(myPort.getSystemPortName());


    }
}

