package com.atomicaggies.gui.avionicsgui;

public class DataParsingException extends Exception {
    // Constructor that accepts a message
    public DataParsingException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause (another Throwable)
    public DataParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}

