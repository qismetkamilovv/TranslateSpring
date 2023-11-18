package com.example.translate.dto;

public class CreateResponse {

    private Long id;
    private String translatedText;

    //TODO create new construktor hansiki bu fiealdleri qebul elesin.
    public CreateResponse(Long id, String translatedtext){
        this.id= id;
        this.translatedText= translatedtext;
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