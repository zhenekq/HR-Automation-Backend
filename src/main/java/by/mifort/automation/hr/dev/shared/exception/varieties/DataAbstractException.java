package by.mifort.automation.hr.dev.shared.exception.varieties;

import org.springframework.http.HttpStatus;

public abstract class DataAbstractException extends RuntimeException {
    private final HttpStatus status;

    public DataAbstractException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}