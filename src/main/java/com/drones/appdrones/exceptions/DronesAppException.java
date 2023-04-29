package com.drones.appdrones.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DronesAppException extends RuntimeException {

    private HttpStatus httpStatus;

    private String errorDescription;

    public DronesAppException(HttpStatus httpStatus, String errorDescription) {
        super(errorDescription);
        this.httpStatus = httpStatus;
        this.errorDescription = errorDescription;
    }

}
