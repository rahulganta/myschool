package com.myschool.adminservice.controlleradvice;

import com.myschool.adminservice.model.ApiError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError handleAllExceptions(Exception ex, WebRequest request) {
        String a = request.getContextPath();
        String b = request.getHeader("Authorization");
        Class<? extends WebRequest> c = request.getClass();
        return new ApiError(LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String a = request.getContextPath();
        String b = request.getHeader("Authorization");
        Class<? extends WebRequest> c = request.getClass();
        Iterator<String> d = request.getHeaderNames();
        return new ApiError(LocalDateTime.now(), ex.getMessage(), ex.getLocalizedMessage(), request.getDescription(false));
    }
}
