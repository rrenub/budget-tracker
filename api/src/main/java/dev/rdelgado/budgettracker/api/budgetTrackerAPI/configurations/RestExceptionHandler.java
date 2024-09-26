package dev.rdelgado.budgettracker.api.budgetTrackerAPI.configurations;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.ErrorDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAppException(AppException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }
}
