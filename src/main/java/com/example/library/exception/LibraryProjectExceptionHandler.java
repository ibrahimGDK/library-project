package com.example.library.exception;


import com.example.library.exception.message.ApiResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class LibraryProjectExceptionHandler {

    Logger logger = LoggerFactory.getLogger(LibraryProjectExceptionHandler.class);

    private ResponseEntity<Object> buildResponseEntity(ApiResponseError error){
        logger.error(error.getMessage());
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ApiResponseError error = new ApiResponseError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        return buildResponseEntity(error);
    }

    protected ResponseEntity<Object> handlerConflictException(ConflictException ex,WebRequest request){
        ApiResponseError error = new ApiResponseError(HttpStatus.CONFLICT, ex.getMessage(),request.getDescription(false));
        return buildResponseEntity(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        ApiResponseError error = new ApiResponseError(HttpStatus.FORBIDDEN,
                ex.getMessage(),
                request.getDescription(false));

        return buildResponseEntity(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false));

        return buildResponseEntity(error);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex, WebRequest request) {
        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false));

        return buildResponseEntity(error);
    }



    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult().
                getFieldErrors().
                stream().
                map(e->e.getDefaultMessage()).
                collect(Collectors.toList());

        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
                errors.get(0).toString(),
                request.getDescription(false));
        return buildResponseEntity(error);
    }


    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false))  ;
        return buildResponseEntity(error);
    }


    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false))  ;
        return buildResponseEntity(error);
    }


    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false))  ;
        return buildResponseEntity(error);
    }



    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {


        ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getDescription(false));
        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralException( Exception ex, WebRequest request) {


        ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR, // parent exception olduğu için genel bir status code seçtik
                ex.getMessage(),
                request.getDescription(false));

        return buildResponseEntity(error);
    }
}
