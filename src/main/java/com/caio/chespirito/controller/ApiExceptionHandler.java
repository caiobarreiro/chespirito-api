package com.caio.chespirito.controller;

import com.caio.chespirito.dto.ErrorResponse;

import java.time.Instant;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleResponseStatusException(
      ResponseStatusException ex,
      HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
    return ResponseEntity.status(status).body(buildError(status, ex.getReason(), request));
  }

  @ExceptionHandler({
      MethodArgumentNotValidException.class,
      MethodArgumentTypeMismatchException.class,
      HttpMessageNotReadableException.class
  })
  public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).body(buildError(status, "Invalid request", request));
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ErrorResponse> handleDataAccess(DataAccessException ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
    return ResponseEntity.status(status).body(buildError(status, "Database error", request));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    return ResponseEntity.status(status).body(buildError(status, "Unexpected error", request));
  }

  private ErrorResponse buildError(HttpStatus status, String message, HttpServletRequest request) {
    String resolvedMessage = message == null || message.isBlank()
        ? status.getReasonPhrase()
        : message;
    return new ErrorResponse(
        Instant.now(),
        status.value(),
        status.getReasonPhrase(),
        resolvedMessage,
        request.getRequestURI()
    );
  }
}
