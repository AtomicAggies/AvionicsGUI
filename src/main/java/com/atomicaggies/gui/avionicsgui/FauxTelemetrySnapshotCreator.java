package com.atomicaggies.gui.avionicsgui;

public class FauxTelemetrySnapshotCreator extends TelemetrySnapshotCreator{
    @Override
    public TelemetrySnapshot createProduct(String rawData) {
        //Parse and return FauxSnapshot
        return null;
    }
}
