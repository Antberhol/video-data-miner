package peertubeminer.peertube.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, Object>> handleClientError(HttpClientErrorException ex, HttpServletRequest request) {
        return build(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Map<String, Object>> handleServerError(HttpServerErrorException ex, HttpServletRequest request) {
        return build(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<Map<String, Object>> handleUnknownStatusError(UnknownHttpStatusCodeException ex, HttpServletRequest request) {
        return build(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpected(Exception ex, HttpServletRequest request) {
        ex.printStackTrace(); // Log the error to console
        String causeMsg = ex.getCause() != null ? " | Causa: " + ex.getCause().getMessage() : "";
        if (ex.getCause() != null && ex.getCause().getCause() != null) {
            causeMsg += " | Subcausa: " + ex.getCause().getCause().getMessage();
        }
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor: " + ex.getMessage() + causeMsg, request.getRequestURI());
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
