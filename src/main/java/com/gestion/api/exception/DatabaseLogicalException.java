package com.gestion.api.exception;

public class DatabaseLogicalException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final String errorCode;
  private final String errorMessage;

  public DatabaseLogicalException(String code, String message) {
    super(code + " - " + message);
    this.errorCode = code;
    this.errorMessage = message;
  }

  public DatabaseLogicalException(String code, String message, Throwable t) {
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
