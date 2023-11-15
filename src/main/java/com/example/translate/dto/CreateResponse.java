package com.example.translate.dto;

public class CreateResponse {

    private Long id;
    private String translatedText;

    //TODO create new construktor hansiki bu fiealdleri qebul elesin.
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