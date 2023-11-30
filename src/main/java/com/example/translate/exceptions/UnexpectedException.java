package com.example.translate.exceptions;

public class UnexpectedException extends RuntimeException{
    public UnexpectedException(String message){
        super(message);
    }
    
    public UnexpectedException(){
        super("unknown error");
    }
}
