package com.inss.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = messageSource.getMessage("message.invalidJson", null, LocaleContextHolder.getLocale());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiErrorArgumentNotValid(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleInssNotFoundException(NotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AlreadyExistingException.class)
    public ResponseEntity<Object> handleAlreadyExistingException(AlreadyExistingException exception) {
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, exception.getMessage()));
    }

    private ApiError createApiErrorArgumentNotValid(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Um ou mais parâmetros são inválidos.");

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ApiErrors subError = new ApiErrors();
            subError.setField(fieldError.getField());
            subError.setMessage(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            apiError.errors.add(subError);
        }
        return apiError;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Data
    public static class ApiError {
        private HttpStatus status;
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String debugMessage;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<ApiErrors> errors = new ArrayList<>();

        private ApiError() {}

        ApiError(HttpStatus status) {
            this();
            this.status = status;
        }

        ApiError(HttpStatus status, String message) {
            this();
            this.status = status;
            this.message = message;
        }

        ApiError(HttpStatus status, String message, Throwable ex) {
            this();
            this.status = status;
            this.message = message;
            this.debugMessage = ex.getLocalizedMessage();
        }
    }

    @Data
    @NoArgsConstructor
    static class ApiErrors {
        private String field;
        private String message;
    }

}