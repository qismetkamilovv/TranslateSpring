package com.example.translate.service;

import java.util.List;
import java.util.Optional;

import com.example.translate.dto.CreateResponse;
import com.example.translate.dto.CreateTranslationDto;
import com.example.translate.entity.Translations;
import com.example.translate.entity.UserInfo;

public interface TranslationService {
    CreateResponse translate(String word);

    CreateResponse translate(String word, String targetLang);

    CreateResponse translate(String sourceLang, String word, String targetLang);

    List<Translations> getAll();
    
    List <Translations>findBySourceText(String sourceText);

    Optional <Translations> findBySourceTextAndTargetLanguage(String word, String targetLang);

    List <Translations> findAllByTargetLanguage(String targetLang);

    List <Translations> findAllBySourceLanguage(String sourceLang);

    List <Translations> findAllBySourceLanguageAndTargetLanguage(String sourceLang, String targetLang);

    void deleteBySourceText(String sourceText) ;
    
    void saveData( CreateTranslationDto newTranslation ) ;

    Translations updateData(Long id, CreateTranslationDto dto) ;

    
    
    

}
