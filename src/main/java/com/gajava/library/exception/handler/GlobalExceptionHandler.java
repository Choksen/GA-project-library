package com.gajava.library.exception.handler;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Global Exception handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidArgumentsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArgumentsException(final InvalidArgumentsException e) {
        final ExceptionResponse response = new ExceptionResponse(e.getMessage(), LocalDate.now(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(final EntityNotFoundException e) {
        final ExceptionResponse response = new ExceptionResponse(e.getMessage(), LocalDate.now(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveEntityException.class)
    public ResponseEntity<ExceptionResponse> handleSaveEntityException(final SaveEntityException e) {
        final ExceptionResponse response = new ExceptionResponse(e.getMessage(), LocalDate.now(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handler on annotation @Valid
     * @param ex exception
     * @param headers header
     * @param status status
     * @param request request
     * @return response object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final List<String> errorMessages = new ArrayList<>();
        for (final FieldError error : fieldErrors) {
            errorMessages.add(error.getDefaultMessage());
        }
        final ExceptionResponse exceptionResponse = new ExceptionResponse(
                "Bad request",
                LocalDate.now(),
                errorMessages);
        return this.handleExceptionInternal(ex, exceptionResponse, headers, status, request);
    }

}
