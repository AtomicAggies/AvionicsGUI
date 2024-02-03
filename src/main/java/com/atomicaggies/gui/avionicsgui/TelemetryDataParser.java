package com.atomicaggies.gui.avionicsgui;

import java.text.ParseException;

public interface TelemetryDataParser {
    TelemetryDataModel parse(String rawData) throws ParseException;

}
