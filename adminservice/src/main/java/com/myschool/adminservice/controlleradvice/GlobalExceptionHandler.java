package com.myschool.adminservice.controlleradvice;

import com.myschool.adminservice.model.ApiError;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError handleAllExceptions(Exception ex, WebRequest request) {
        return new ApiError(LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAuthorizationException(AuthenticationException ex, WebRequest request) {
        return new ApiError(LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiError handleAccessDeniedException(AccessDeniedException ex, WebRequest request){
        String errorMessage = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.getMessage();
        return new ApiError(LocalDateTime.now(), errorMessage , ex.getLocalizedMessage(), request.getDescription(false));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String errorMessage = ex.getMostSpecificCause().getLocalizedMessage() != null ? ex.getMostSpecificCause().getLocalizedMessage() : ex.getMessage();
        return new ApiError(LocalDateTime.now(), errorMessage , ex.getLocalizedMessage(), request.getDescription(false));
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseEntity<ApiError> handleValidationError(MethodArgumentNotValidException ex, WebRequest request) {
//        new ApiError(LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false));
//
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Validation error : "+ex);
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ApiError apiError = new ApiError(LocalDateTime.now(), errorMessage, ex.getLocalizedMessage(), request.getDescription(false));

        /*TODO add errorlist prop to ApiError object and add the validation errors to it either Map or List depending on UI requirements*/
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        /*apiError.setErrorList(validationList);*/
        Map<String, String> errorList = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String eMessage = error.getDefaultMessage();
            errorList.put(fieldName, eMessage);
        });

        return new ResponseEntity<>(apiError, status);
    }
}
