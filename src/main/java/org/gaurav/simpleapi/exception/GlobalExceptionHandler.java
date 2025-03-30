package org.gaurav.simpleapi.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.apache.catalina.connector.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request){
        Optional<Set<ConstraintViolation<?>>> x = Optional.ofNullable(e).map(ConstraintViolationException::getConstraintViolations);
        if(x.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getConstraintViolations().stream().findFirst().map(constraintViolation -> constraintViolation.getMessage()));
        } else {
            return handleException(e, request);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult()
                .getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException e, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());

    }
}
