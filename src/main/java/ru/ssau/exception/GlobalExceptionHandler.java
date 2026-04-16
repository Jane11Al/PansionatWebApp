package ru.ssau.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String TIMESTAMP_STR = "timestamp";
    private static final String STATUS_STR = "status";
    private static final String ERROR_STR = "error";
    private static final String MESSAGE_STR = "message";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(EntityNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_STR, LocalDateTime.now());
        body.put(STATUS_STR, HttpStatus.NOT_FOUND.value());
        body.put(ERROR_STR, "Not Found");
        body.put(MESSAGE_STR, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<Object> handleDuplicate(DuplicateEntityException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_STR, LocalDateTime.now());
        body.put(STATUS_STR, HttpStatus.CONFLICT.value());
        body.put(ERROR_STR, "Conflict");
        body.put(MESSAGE_STR, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_STR, LocalDateTime.now());
        body.put(STATUS_STR, HttpStatus.BAD_REQUEST.value());
        body.put(ERROR_STR, "Bad Request");
        body.put(MESSAGE_STR, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_STR, LocalDateTime.now());
        body.put(STATUS_STR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(ERROR_STR, "Internal Server Error");
        body.put(MESSAGE_STR, "Произошла непредвиденная ошибка");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}