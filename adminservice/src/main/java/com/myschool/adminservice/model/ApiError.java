package com.myschool.adminservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import java.util.Map;

@Data
@NoArgsConstructor
@ToString
public class ApiError {
    /*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")*/
    private LocalDateTime timestamp;
    private String errorCode;
    private String message;
    private String details;
    private String path;

    private Map<String, String> properties;

    public ApiError(LocalDateTime timestamp, String errorCode, String message) {
        super();
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
    }
    public ApiError(LocalDateTime timestamp, String errorCode, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }
    public ApiError(LocalDateTime timestamp, String errorCode, String message, String details, String path) {
        super();
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.path = path;
    }
    public ApiError(LocalDateTime timestamp, String errorCode, String message, String details, String path, Map<String,String> properties) {
        super();
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.path = path;
        this.properties = properties;
    }

}
