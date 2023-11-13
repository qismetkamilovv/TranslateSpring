package com.example.translate.service;

import java.util.List;
import java.util.Optional;

import com.example.translate.dto.CreateTranslationDto;
import com.example.translate.entity.Translations;

public interface TranslationService {
    String translate(String word);

    String translate(String word, String targetLang);

    String translate(String sourceLang, String word, String targetLang);

    List<Translations> getAll();
    
    Translations findBySourceText(String sourceText);

    Optional <Translations> findBySourceTextAndTargetLanguage(String word, String targetLang);

    List <Translations> findAllByTargetLanguage(String targetLang);

    List <Translations> findAllBySourceLanguage(String sourceLang);

    List <Translations> findAllBySourceLanguageAndTargetLanguage(String sourceLang, String targetLang);

    void deleteBySourceText(String sourceText) ;
    
    void saveData( CreateTranslationDto newTranslation ) ;

    Translations updateData(Long id, CreateTranslationDto dto) ;
    
    

}
