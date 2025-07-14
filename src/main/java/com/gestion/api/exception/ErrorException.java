package com.gestion.api.exception;

public class ErrorException extends RuntimeException {
  private final String errorCode;
  private final String errorMessage;

  public ErrorException(String code, String message) {
    super(code + " - " + message);
    this.errorCode = code;
    this.errorMessage = message;
  }

  public ErrorException(String code, String message, Throwable t) {
    super(code + " - " + message, t);
    this.errorCode = code;
    this.errorMessage = message;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
