package com.example.translate;


import org.springframework.stereotype.Component;
@Component
public class GoogleTranslatorApiClient {
    
     public String translate(String sourceLang, String targetLang, String text) {
        // String translatedText = null;

        // String requestJson = JsonHelper.toJson(sourceLang, targetLang, text);
        // HttpResponse<String> response = HttpHelper.post(requestJson);

        // translatedText = JsonHelper.getTranslatedText(response.body());

        // return translatedText;
        return text ;
    }
}
