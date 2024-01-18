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
public class GoogleTranslatorApiClientOld {

    private final RestTemplate restTemplate;

    public GoogleTranslatorApiClientOld(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String translate(String sourceLang, String targetLang, String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer ya29.a0AfB_byD_dOBCwP7GXWWm6Pyw5ojbh4dzeyVu4UEoUNvXIqrCgVS_cd6izUIc42YZp9zVTcKtqzg_YYR5wlslHYRK3g_gCLIcGtCiKXdwEg8mi7dfUDVlsDRHDqT2y16VFGID61CtU6yxL9dtWvJyAzrrF6SvF9zI1bXhzsSFensjaCgYKAYcSARESFQHGX2MinF869KB3rTz14g9S7mnWiA0179");
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

