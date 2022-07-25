package by.mifort.automation.hr.dev.shared.exception.varieties;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends DataAbstractException {
    public DataNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
