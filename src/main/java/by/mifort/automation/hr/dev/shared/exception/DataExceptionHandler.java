package by.mifort.automation.hr.dev.shared.exception;

import by.mifort.automation.hr.dev.shared.exception.data.ResponseErrorBody;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataAbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class DataExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResponseErrorBody> handleException(DataAbstractException candidateCreateException) {
        return new ResponseEntity<>(new ResponseErrorBody(
                candidateCreateException.getStatus().name(),
                candidateCreateException.getStatus().value(),
                candidateCreateException.getMessage()), candidateCreateException.getStatus());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<ResponseErrorBody>> handleException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(buildMethodValidationErrors(exception.getAllErrors()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<ResponseErrorBody>> handleException(BindException exception) {
        return new ResponseEntity<>(buildMethodValidationErrors(exception.getAllErrors()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<ResponseErrorBody>> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(buildValidationErrors(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResponseErrorBody> handleException(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(new ResponseErrorBody(
                exception.getName(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResponseErrorBody> handleException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ResponseErrorBody(
                "Empty body",
                HttpStatus.BAD_REQUEST.value(),
                "Body should not be empty"), HttpStatus.BAD_REQUEST);
    }

    private List<ResponseErrorBody> buildValidationErrors(
            Set<ConstraintViolation<?>> violations) {
        return violations.
                stream().
                map(violation ->
                        new ResponseErrorBody(
                                StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                        .reduce((first, second) -> second)
                                        .orElse(null)
                                        .toString(),
                                HttpStatus.BAD_REQUEST.value(),
                                violation.getMessage()
                        )).collect(toList());
    }

    private List<ResponseErrorBody> buildMethodValidationErrors(
            List<ObjectError> violations) {
        return violations.
                stream().
                map(violation ->
                        new ResponseErrorBody(
                                violation.getArguments()[0].toString(),
                                HttpStatus.BAD_REQUEST.value(),
                                violation.getDefaultMessage()
                        )).collect(toList());
    }
}
