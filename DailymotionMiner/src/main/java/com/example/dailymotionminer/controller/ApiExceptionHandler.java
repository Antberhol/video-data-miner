package com.example.dailymotionminer.controller;

import com.example.dailymotionminer.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpClientError(HttpClientErrorException ex, HttpServletRequest request) {
        return build(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpServerError(HttpServerErrorException ex, HttpServletRequest request) {
        return build(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<Map<String, Object>> handleUnknownHttpStatusError(UnknownHttpStatusCodeException ex, HttpServletRequest request) {
        return build(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpected(Exception ex, HttpServletRequest request) {
        ex.printStackTrace(); // Imprime la traza de la excepcin en consola para depuracin
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", request.getRequestURI());
    }

    private ResponseEntity<Map<String, Object>> build(HttpStatus status, String message, String path) {
        return ResponseEntity.status(status).body(Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message,
                "path", path
        ));
    }
}
