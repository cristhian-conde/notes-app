package com.notes.app_notes.controller.exception;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

public record ValidationErrorResponse(
        List<FieldErrorDetail> errors
) {
    public record FieldErrorDetail(
            String field,
            String message
    ) {}

    public static ValidationErrorResponse fromBindingResult(MethodArgumentNotValidException ex) {
        List<FieldErrorDetail> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new FieldErrorDetail(fieldName, errorMessage));
        });
        return new ValidationErrorResponse(errors);
    }
}
