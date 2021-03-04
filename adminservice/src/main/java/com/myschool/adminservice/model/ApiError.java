package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class ApiError {
    /*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")*/
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String path;
    public ApiError(LocalDateTime timestamp, String message, String details, String path) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.path = path;
    }

}
