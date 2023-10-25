package com.example.translate.service;

import java.util.List;
import java.util.Optional;

import com.example.translate.entity.Translations;

public interface TranslationService {
    String translate(String word);

    String translate(String word, String targetLang);

    String translate(String sourceLang,String word , String targetLang);
    
    List<Translations> getAll();

    Translations getBySourceText(String sourceText) ;

    Optional<Translations> translationSearch(String sourceText, String targetLang) ;

    Optional<Translations> findBySourceTextAndTargetLang(String word, String targetLang);

}
