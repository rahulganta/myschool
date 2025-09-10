package com.myschool.adminservice.exceptions;

public class EntityAlreadyExistsException extends RuntimeException
{
    private final String errorKey;
    private final String errorMessage;
    private final String errorCode;
    private final Object[] args;

    public EntityAlreadyExistsException(String errorKey, String errorMessage, String errorCode, Object[] args, Throwable cause) {
        super(errorMessage, cause);
        this.errorKey = errorKey;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.args = args;
    }

    public EntityAlreadyExistsException(String errorKey, String errorMessage, String errorCode, Object[] args) {
        this(errorKey, errorMessage, errorCode, args, null);
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
