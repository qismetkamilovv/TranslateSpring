package com.example.translate.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.translate.dto.TranslationRequest;
import com.example.translate.dto.TranslationResponse;

@Component
public class GoogleTranslatorApiClient {

    private final RestTemplate restTemplate;

    public GoogleTranslatorApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String translate(String sourceLang, String targetLang, String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer ya29.a0AfB_byCP6By6nsB-5oDz0hpWt_AXYLZWc76ZBy-VAJYuIz8PECW6VB2W84oW5AnWUQK8RMIOGua2QcBPZehSVm1gq4kaRS4TacIX7GxQ4wU70E2prOKOEUPRQQQNoM0lScvqSVLdnhu-btL8xM2IZAthSDvm2AUg58ozB9gRzugaCgYKATcSARESFQGOcNnCFzLomorhwOAVWCBxpQxYGw0178");
        headers.set("x-Goog-User-Project", "translatorapp-398317");
        headers.set("Content-type", "application/json;charset=utf-8");

        TranslationRequest requestBody = new TranslationRequest();
        requestBody.setQ(text);
        requestBody.setSource(sourceLang);
        requestBody.setTarget(targetLang);

        HttpEntity<TranslationRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        String url = "https://translation.googleapis.com/language/translate/v2";
        ResponseEntity<TranslationResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, TranslationResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            TranslationResponse responseBody = response.getBody();
            return responseBody.getData().getTranslations().get(0).getTranslatedText();
        } else {
            
        }
        return null ;
    }
}

