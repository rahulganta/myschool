package com.myschool.adminservice.controlleradvice;

import com.myschool.adminservice.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ResourceBundleMessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError handleAllExceptions(Exception ex, WebRequest request) {
        return new ApiError(LocalDateTime.now(), "", ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false), Collections.emptyMap());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAuthorizationException(AuthenticationException ex, WebRequest request) {
        return new ApiError(LocalDateTime.now(), "", ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false), Collections.emptyMap());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiError handleAccessDeniedException(AccessDeniedException ex, WebRequest request){
        String errorMessage = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.getMessage();
        return new ApiError(LocalDateTime.now(), "", errorMessage , ex.getLocalizedMessage(), request.getDescription(false), Collections.emptyMap());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String errorMessage = ex.getMostSpecificCause().getLocalizedMessage() != null ? ex.getMostSpecificCause().getLocalizedMessage() : ex.getMessage();
        return new ApiError(LocalDateTime.now(), "", errorMessage , ex.getLocalizedMessage(), request.getDescription(false), Collections.emptyMap());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseEntity<ApiError> handleValidationError(MethodArgumentNotValidException ex, WebRequest request) {
//        new ApiError(LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false));
//
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Validation error : "+ex);
        Locale locale = LocaleContextHolder.getLocale();

        Object [] firstFieldArguments = ex.getBindingResult().getFieldErrors().get(0).getArguments();
        String firstErrorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        firstErrorMessage = messageSource.getMessage(firstErrorMessage, firstFieldArguments, firstErrorMessage, locale);

        Map<String, String> errorList = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Object [] obj = error.getArguments();
            String fieldName = ((FieldError) error).getField();
            String eMessage = messageSource.getMessage(error.getDefaultMessage(), obj, error.getDefaultMessage(), locale);
            errorList.put(fieldName, eMessage);
        });
        ApiError apiError = new ApiError(LocalDateTime.now(), "", firstErrorMessage, ex.getLocalizedMessage(), request.getDescription(false), errorList);
        return new ResponseEntity<>(apiError, status);
    }
}
