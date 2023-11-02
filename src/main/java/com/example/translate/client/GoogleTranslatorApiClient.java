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
        headers.set("Authorization", "Bearer ya29.a0AfB_byA6hY19BZkNR-oAmZhnexENMf1Qz8tgnjr4ts264a5YuPNIqfqJkwOXiRCYC9yL15Hd0-SnXRl91jXhrfLHl6Z2XIuFKmThUg7GF5iaCx7Il41Iddgcgciuq12KaGhsdWAZ_SjcLeAmyiaQ0J2gqZPU4xgd45YcnSNPGYgaCgYKAcwSARESFQGOcNnCWaEP7kRFkCtKQAEvX4kjSw0178");
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

