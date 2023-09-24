package com.example.translate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository

public class TranslateRepository {
    
    private static final String INSERT_QUERY = "INSERT INTO translations (source_language, target_language, source_text, translated_text) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT translated_text FROM translations WHERE source_text = ?";

    
    public void save(){
        
    }

    public void findByWord(){

    }
}