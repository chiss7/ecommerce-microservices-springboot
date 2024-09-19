package com.chis.ecommerce.handler;

import com.chis.ecommerce.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * Allows intercepting exceptions thrown by any controller in the application.
 * It provides a centralized mechanism for handling exceptions, instead of having repetitive code in multiple controllers.
 * It ensures that responses returned by exception handler methods are automatically serialized in JSON format.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method will handle BusinessException exceptions thrown anywhere in the application.
     * @param exp
     * @return ResponseEntity<String> with http status 400 and error message in body.
     */
    @ExceptionHandler(BusinessException.class) // This method should be called when BusinessException occurs.
    public ResponseEntity<String> handle(BusinessException exp) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exp.getMsg());
    }

    @ExceptionHandler(EntityNotFoundException.class) // This method should be called when EntityNotFoundException occurs.
    public ResponseEntity<String> handle(EntityNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exp.getMessage());
    }

    /**
     * This method will handle exceptions of type MethodArgumentNotValidException,
     * which are thrown when the validation of a method argument in a controller fails.
     * @param exp
     * @return ResponseEntity<ErrorResponse> with http status 400 and ErrorResponse in body.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors() // to get a list of all validation errors.
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField(); // extract the name of the field
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}
