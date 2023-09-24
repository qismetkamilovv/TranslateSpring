package com.example.translate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.Entity;



public class DataSourceConfig {
    
    private final static String URL = "jdbc:mysql://localhost:3306/translator_app";
    private final static String USER = "root";
    private final static String PASSWORD = "qismet20";

    public void connect(){

    }
    public void close(){

    }
}
