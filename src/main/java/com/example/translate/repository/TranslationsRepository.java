package com.example.translate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.translate.entity.Translations;

@Repository
public interface TranslationsRepository extends JpaRepository<Translations, Long > {

    Optional<Translations>  findBySourceText(String sourceText) ;

    Optional <Translations> findBySourceTextAndTargetLang(String word, String targetlang);

    Optional <Translations> translationSearch(String sourceText, String targetLang) ;
}
