package com.example.translate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.translate.entity.Translations;

@Repository
public interface TranslationsRepository extends JpaRepository<Translations, Long > {

    Optional<Translations>  findBySourceText(String sourceText) ;
    // select*from Translations_app where source_text=?

    Optional <Translations> findBySourceTextAndTargetLanguage(String word, String targetlang);

    List<Translations> findAllByTargetLanguage(String targetLang) ;

    // @Query("select t from Translations t where t.sourceLanguage = ?")
    List<Translations> findAllBySourceLanguage(String sourcelang) ;

    List <Translations> findAllBySourceLanguageAndTargetLanguage(String sourceLang, String targetLang);

    // @Query(value = "select * from translations where target_language = :tLang and source_language = :sLang", nativeQuery = true)
    List <Translations> selectAllBy(String sLang, String tLang);
}
