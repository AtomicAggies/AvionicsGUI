package com.atomicaggies.gui.avionicsgui;

import java.text.ParseException;

public interface TelemetryDataParser {
    TelemetrySnapshotDTO parse(String rawData,TelemetryDataModel model) throws DataParsingException;

}
