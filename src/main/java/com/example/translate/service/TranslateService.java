package com.example.translate.service;

public interface TranslateService {
    String translate(String word);

    String translate(String word, String targetLang);

    String translate(String word, String sourceLang, String targetLang);
    
}
