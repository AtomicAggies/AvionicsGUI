package com.atomicaggies.gui.avionicsgui;

public class FauxTelemetrySnapshotCreator extends TelemetrySnapshotCreator{
    private final int PARTS_LEN = 5;
    @Override
    public TelemetrySnapshot createProduct(String rawData) throws DataParsingException {
        //Parse and return FauxSnapshot

        // Expected format: "GSDATA,100000,28.20,36.20,ENDGS"
        // "GSDATA,timestamp,temp,humidity,ENDGS"
        rawData = rawData.strip().replace("\n","");
        if (!rawData.startsWith("GSDATA") || !rawData.endsWith("ENDGS")) {
            throw new DataParsingException("Invalid data format");
        }

            String[] parts = rawData.split(",");
            if (parts.length != PARTS_LEN) throw new DataParsingException("Incorrect number of data parts");
        try{
            double time = Double.parseDouble(parts[1]);
            double temperature = Double.parseDouble(parts[2]);
            double humidity = Double.parseDouble(parts[3]);

            TelemetrySnapshot snapshot = new FauxTelemetrySnapshot(time,temperature,humidity);
            return snapshot;
        } catch (NumberFormatException e) {
            throw new DataParsingException("Error parsing numerical values", e);
        }
    }
}
