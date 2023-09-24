package com.example.translate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService {
    

    public final static String SOURCE_LANG = "en";
    public final static String TARGET_LANG = "az";

    private GoogleTranslatorApiClient googleTranslatorApiClient;
    private TranslateRepository translateRepostiry;






    @Override
    public String translate(String word) {
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }

    @Override
    public String translate(String word, String targetLang) {
        
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }

    @Override
    public String translate(String word, String sourceLang, String targetLang) {
       
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }
    
}
