package by.mifort.automation.hr.dev.candidate.handler.exception.varieties;

import by.mifort.automation.hr.dev.candidate.handler.exception.CandidateAbstractException;
import org.springframework.http.HttpStatus;

public class CandidateCreateException extends CandidateAbstractException {
    public CandidateCreateException(String message, HttpStatus status) {
        super(message, status);
    }
}
