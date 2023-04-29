package com.drones.appdrones.controllers.exceptions;

import com.drones.appdrones.exceptions.DronesAppException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice(basePackages = "com.drones.appdrones.controllers")
public class DronesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintException(final Exception ex) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ApiError response = new ApiError(httpStatus.toString(), ex.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler({DronesAppException.class})
    public ResponseEntity<ApiError> handleDroneException(final DronesAppException ex) {
        final HttpStatus httpStatus = ex.getHttpStatus();
        final ApiError response = new ApiError(httpStatus.toString(), ex.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    @Getter
    @AllArgsConstructor
    protected static class ApiError {

        private String errorCode;

        @JsonProperty("error_description")
        private String errorDescription;
    }

}
