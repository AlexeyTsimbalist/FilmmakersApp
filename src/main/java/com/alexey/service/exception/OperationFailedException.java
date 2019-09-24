package com.alexey.service.exception;

public class OperationFailedException extends RuntimeException{
    public OperationFailedException(String message){super(message);}
}
