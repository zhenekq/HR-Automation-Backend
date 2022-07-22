package by.mifort.automation.hr.dev.candidate.handler.exception;

import org.springframework.http.HttpStatus;

public abstract class CandidateAbstractException extends RuntimeException {
    private final HttpStatus status;

    public CandidateAbstractException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}