package ru.emitrohin.roox.util;

/**
 * Utilities that check condition and if necessary throws exceptions
 *
 * @author Evgeniy Mitrokhin
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(object != null, message);
        return object;
    }

    private static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Entity wasn't found: " + message);
        }
    }

}
