package dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
