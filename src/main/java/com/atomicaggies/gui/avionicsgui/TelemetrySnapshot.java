package com.atomicaggies.gui.avionicsgui;

public abstract class TelemetrySnapshot {
    public TelemetrySnapshot() {
    }

    //get timestamp etc

    // Abstract method to be implemented by concrete classes to update the model
    public abstract void updateModel(TelemetryDataModel model);
}

