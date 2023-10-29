package com.example.translate.dto;

public class Response {
    public int status ;
    public String message ;
    
    public Response(int status, String message){
        this.status=status ;
        this.message=message ;
    }
}
