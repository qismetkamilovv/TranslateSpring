package com.example.translate.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Translations")

public class Translations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id ; // todo rename to all lowercase

    @JsonProperty("sourceLang")
    @Column(name = "source_language")
    private String sourceLanguage ;

    @Column(name = "target_language")
    private String targetLanguage ;

    @Column(name = "source_text")
    private String sourceText ;

    @Column(name = "translated_text")
    private String translatedText;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id ;
        
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourcelanguage) {
        this.sourceLanguage = sourcelanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetlanguage) {
        this.targetLanguage = targetlanguage;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourcetext) {
        this.sourceText = sourcetext;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedtext) {
        this.translatedText = translatedtext;
    }

    
}
