package dev.rdelgado.budgettracker.api.budgetTrackerAPI.configurations;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.ErrorDto;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder strBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();
            } catch (ClassCastException ex1) {
                fieldName = error.getObjectName();
            }

            String message = error.getDefaultMessage();
            strBuilder.append(String.format("%s: %s", fieldName, message));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(strBuilder.toString()));
    }
}
