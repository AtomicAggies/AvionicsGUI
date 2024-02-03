package com.atomicaggies.gui.avionicsgui;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.core.Observable;

public class TelemetryDataModel {

    //Constructor
    public TelemetryDataModel() {
    }


    private BehaviorSubject<Double> timeSubject = BehaviorSubject.create();
    private BehaviorSubject<Double> temperatureSubject = BehaviorSubject.create();
    private BehaviorSubject<Double> humiditySubject = BehaviorSubject.create();

    public Observable<Double> getTimeObservable() {
        return timeSubject.hide();
    }
    public Observable<Double> getTemperatureObservable() {
        return temperatureSubject.hide();
    }

    public Observable<Double> getHumidityObservable() {
        return humiditySubject.hide();
    }

    public void updateTime(double newTime) {
        timeSubject.onNext(newTime);
    }
    public void updateTemperature(double newTemperature) {
        temperatureSubject.onNext(newTemperature);
    }
    public void updateHumidity(double newHumidity) {
        humiditySubject.onNext(newHumidity);
    }
}

