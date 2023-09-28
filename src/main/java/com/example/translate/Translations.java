package com.example.translate;

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
    private Long Id ;

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
        Id = id;
    }

    public String getSource_language() {
        return sourceLanguage;
    }

    public void setSource_language(String source_language) {
        this.sourceLanguage = source_language;
    }

    public String getTarget_language() {
        return targetLanguage;
    }

    public void setTarget_language(String target_language) {
        this.targetLanguage = target_language;
    }

    public String getSource_text() {
        return sourceText;
    }

    public void setSource_text(String source_text) {
        this.sourceText = source_text;
    }

    public String getTranslated_text() {
        return translatedText;
    }

    public void setTranslated_text(String translated_text) {
        this.translatedText = translated_text;
    }

    
}
