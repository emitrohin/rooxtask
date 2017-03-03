package ru.emitrohin.roox.util;

/**
 * This exception is thrown when nothing was found in database
 *
 * @author Evgeniy Mitrokhin
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
