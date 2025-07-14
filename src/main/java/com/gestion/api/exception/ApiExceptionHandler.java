package com.gestion.api.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.gestion.api.config.JwtTokenUtil;
import com.gestion.api.model.ApiResponse;
import com.gestion.api.model.ApiResponseException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final JwtTokenUtil jwtTokenUtil;

    ApiExceptionHandler(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ApiResponseException> handleUnAuthorized(UnAuthorizedException ex,
            HttpServletRequest request) {
        String path = request.getRequestURI();

        if (isSwaggerPath(path)) {
            throw new SwaggerApiDocsException(ex); // Deja que Spring maneje estas rutas
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseException("401", "El usuario no tiene permisos para acceder a este recurso"));

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponseException> handleNotFound(NoHandlerFoundException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        if (isSwaggerPath(path)) {
            throw new SwaggerApiDocsException(ex); // Deja que Spring maneje estas rutas
        }
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseException("500", "El recurso solicitado no existe"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseException> handleBadRequest(MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String path = request.getRequestURI();
        if (isSwaggerPath(path)) {
            throw new SwaggerApiDocsException(ex); // Deja que Spring maneje estas rutas
        }

        String errorMessage = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseException("500", errorMessage));
        
        
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ApiResponseException> handleBadRequest(ErrorException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        if (isSwaggerPath(path)) {
            throw new SwaggerApiDocsException(ex);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseException(ex.getErrorCode(), ex.getErrorMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseException> handleAllExceptions(Exception ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        if (isSwaggerPath(path)) {
            throw new SwaggerApiDocsException(ex);
        }
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseException("500", ex.getMessage()));
    }

    private boolean isSwaggerPath(String path) {
        return path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui");
    }

}
