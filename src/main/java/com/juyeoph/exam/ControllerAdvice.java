package com.juyeoph.exam;

import com.juyeoph.utils.TodoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(TodoException.class)
    public ResponseEntity<Map<String, Object>> handle(final HttpServletRequest request) {

        final HttpStatus httpStatus = HttpStatus.NOT_IMPLEMENTED;
        final Map<String, Object> body = Map.of(
            "timestamp", Instant.now(),
            "status", httpStatus.value(),
            "error", httpStatus.getReasonPhrase(),
            "path", request.getRequestURI()
        );
        return ResponseEntity.status(httpStatus).body(body);
    }
}
