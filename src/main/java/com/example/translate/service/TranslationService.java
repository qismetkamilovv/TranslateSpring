package com.example.translate.service;

import java.util.List;

import com.example.translate.entity.Translations;

public interface TranslationService {
    String translate(String word);

    String translate(String word, String targetLang);

    String translate(String word, String sourceLang, String targetLang);
    
    List<Translations> getAll();

    Translations getBySourceText(String source_text) ;

}
