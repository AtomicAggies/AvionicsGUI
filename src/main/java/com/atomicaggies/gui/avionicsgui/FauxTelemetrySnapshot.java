package com.atomicaggies.gui.avionicsgui;

public class FauxTelemetrySnapshot extends TelemetrySnapshot {
    private final double timestamp;
    private final double temperature;
    private final double humidity;

    public FauxTelemetrySnapshot(double timestamp, double temperature, double humidity) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public void updateModel(TelemetryDataModel model) {
        model.updateTime(timestamp);
        model.updateTemperature(temperature);
        model.updateHumidity(humidity);
    }

    // Getters if needed
}

