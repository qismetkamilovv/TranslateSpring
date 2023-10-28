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
        headers.set("Authorization", "Bearer ya29.a0AfB_byAdX0lC8lcuw8SCIO8rVLnIk5OXO0KLdDRjHuZyCD3csMP63y1qca_cqLA9nGAsO56i7563dllVl2qWHd6T6KazxR1-Y4YRKFVYBe8xMf9OprsZCi-0n6tbxZ5Y92JlallPxsoptruSnpA7s_XTK7QeuzLTDaMBKAvVBZEaCgYKAasSARESFQGOcNnCZLh3dfrUjrmMIR1WZ1e31A0178");
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

