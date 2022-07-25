package by.mifort.automation.hr.dev.shared.exception.varieties;

import org.springframework.http.HttpStatus;

public class DataCreateException extends DataAbstractException {
    public DataCreateException(String message, HttpStatus status) {
        super(message, status);
    }
}
