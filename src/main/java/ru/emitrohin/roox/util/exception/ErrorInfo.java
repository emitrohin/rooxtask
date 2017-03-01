package ru.emitrohin.roox.util.exception;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class ErrorInfo {

    private final String url;
    private final String cause;
    private final String[] details;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this(url, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    public ErrorInfo(CharSequence requestURL, String cause, String... details) {
        this.url = requestURL.toString();
        this.cause = cause;
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public String getCause() {
        return cause;
    }

    public String[] getDetails() {
        return details;
    }
}
