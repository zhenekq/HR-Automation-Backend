package by.mifort.automation.hr.dev.candidate.handler;

import by.mifort.automation.hr.dev.candidate.handler.exception.CandidateAbstractException;
import by.mifort.automation.hr.dev.candidate.handler.exception.CandidateErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class CandidateExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<CandidateErrorBody> handleException(CandidateAbstractException candidateCreateException) {
        return new ResponseEntity<>(new CandidateErrorBody(
                candidateCreateException.getStatus().name(),
                candidateCreateException.getStatus().value(),
                candidateCreateException.getMessage()), candidateCreateException.getStatus());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<CandidateErrorBody>> handleException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(buildMethodValidationErrors(exception.getAllErrors()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<CandidateErrorBody>> handleException(BindException exception) {
        return new ResponseEntity<>(buildMethodValidationErrors(exception.getAllErrors()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<CandidateErrorBody>> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(buildValidationErrors(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    private List<CandidateErrorBody> buildValidationErrors(
            Set<ConstraintViolation<?>> violations) {
        return violations.
                stream().
                map(violation ->
                        new CandidateErrorBody(
                                StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                        .reduce((first, second) -> second)
                                        .orElse(null)
                                        .toString(),
                                HttpStatus.BAD_REQUEST.value(),
                                violation.getMessage()
                        )).collect(toList());
    }

    private List<CandidateErrorBody> buildMethodValidationErrors(
            List<ObjectError> violations) {
        return violations.
                stream().
                map(violation ->
                        new CandidateErrorBody(
                                violation.getArguments()[0].toString(),
                                HttpStatus.BAD_REQUEST.value(),
                                violation.getDefaultMessage()
                        )).collect(toList());
    }
}
