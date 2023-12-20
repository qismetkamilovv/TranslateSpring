package com.example.translate.client;

import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GoogleTranslateApiClient {

    private static final String PROJECT_ID = "translator-387919";

    public String translate(String sourceLanguage, String targetLanguage, String text) {

        try (TranslationServiceClient client = TranslationServiceClient.create()) {

            LocationName parent = LocationName.of(PROJECT_ID, "global");

            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent.toString())
                    .setMimeType("text/plain")
                    .setSourceLanguageCode(sourceLanguage)
                    .setTargetLanguageCode(targetLanguage)
                    .addContents(text)
                    .build();

            TranslateTextResponse response = client.translateText(request);
            List<Translation> translations =  response.getTranslationsList() ;

            for (Translation translation :translations) {
                System.out.printf("Translated text %s/n", translation.getTranslatedText());
            }

            return translations.get(0).getTranslatedText() ;

        } catch (IOException e) {
            e.printStackTrace();
            return null ;
        }

    }
}
