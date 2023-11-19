package com.example.translate.dto;

import com.example.translate.entity.Translations;

public class CreateResponse {

    private Long id;
    private String translatedText;

    public CreateResponse(Translations translations) {
        this.id = translations.getId();
        this.translatedText = translations.getTranslatedText();
    }

    public CreateResponse(Long id, String translatedtext) {
        this.id = id;
        this.translatedText = translatedtext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

}