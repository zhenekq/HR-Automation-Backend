package by.mifort.automation.hr.dev.candidate.handler.exception.varieties;

import by.mifort.automation.hr.dev.candidate.handler.exception.CandidateAbstractException;
import org.springframework.http.HttpStatus;

public class CandidateNotFoundException extends CandidateAbstractException {
    public CandidateNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
