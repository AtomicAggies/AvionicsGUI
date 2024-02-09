package com.atomicaggies.gui.avionicsgui;

public class GsDataParser implements TelemetryDataParser {
    private final int parts_len = 5;
    @Override
    public TelemetrySnapshotDTO parse(String rawData,TelemetryDataModel model) throws DataParsingException {
        // Expected format: "GSDATA,100000,28.20,36.20,ENDGS"
        // "GSDATA,timestamp,temp,humidity,ENDGS"
        rawData = rawData.strip().replace("\n","");
        if (!rawData.startsWith("GSDATA") || !rawData.endsWith("ENDGS")) {
            throw new DataParsingException("Invalid data format");
        }

        try {
            String[] parts = rawData.split(",");
            if (parts.length != parts_len) throw new DataParsingException("Incorrect number of data parts");

            double time = Double.parseDouble(parts[1]);
            double temperature = Double.parseDouble(parts[2]);
            double humidity = Double.parseDouble(parts[3]);

            TelemetrySnapshotDTO snapshot = new TelemetrySnapshotDTO(time,temperature,humidity);

            return snapshot;
        } catch (NumberFormatException e) {
            throw new DataParsingException("Error parsing numerical values", e);
        }
    }
}

