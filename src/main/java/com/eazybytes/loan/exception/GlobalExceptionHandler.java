package com.eazybytes.loan.exception;

import com.eazybytes.loan.dto.ErrorResponseDto;
import com.eazybytes.loan.dto.ValidationErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.NOT_FOUND.toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        }
        ValidationErrorResponseDTO errorResponseDTO = new ValidationErrorResponseDTO(
                apiPath,
                HttpStatus.BAD_REQUEST.toString(),
                "There are some input validation errors.",
                errors.size(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> constraintViolationExceptionHandler(ConstraintViolationException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        List<String> errors = new ArrayList<>();
        for (String str : exception.getMessage().split(",")) {
            errors.add(str.split(":")[1].trim());
        }
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.BAD_REQUEST.toString(),
                errors.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}
