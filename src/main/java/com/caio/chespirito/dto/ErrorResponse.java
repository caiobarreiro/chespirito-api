package com.caio.chespirito.dto;

import java.time.Instant;

public class ErrorResponse {
  public final Instant timestamp;
  public final int status;
  public final String error;
  public final String message;
  public final String path;

  public ErrorResponse(Instant timestamp, int status, String error, String message, String path) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }
}
