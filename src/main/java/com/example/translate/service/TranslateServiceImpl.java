package com.example.translate.service;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.stereotype.Service;

import com.example.translate.GoogleTranslatorApiClient;
import com.example.translate.TranslateRepository;

@Service
public class TranslateServiceImpl implements TranslateService {

    private static final Logger LOGGER = Logger.getLogger(TranslateServiceImpl.class.getName());

    public final static String SOURCE_LANG = "en";
    public final static String TARGET_LANG = "az";
    
    private GoogleTranslatorApiClient googleTranslatorApiClient;
    private TranslateRepository translateRepository;

    public TranslateServiceImpl(TranslateRepository repo, GoogleTranslatorApiClient client) {
        this.googleTranslatorApiClient = client;
        this.translateRepository = repo;
    }

    @Override
    public String translate(String text) {
        return this.translate(text, SOURCE_LANG, TARGET_LANG);
    }

    @Override
    public String translate(String text, String targetLang) {
        return this.translate(text, SOURCE_LANG, targetLang);
    }

    @Override
    public String translate(String text, String sourceLang, String targetLang) {
        LOGGER.log(Level.INFO, "Text: {0}, Source lang: {1}, Target lang: {2}",
                new Object[] { text, sourceLang, targetLang });
        String translatedText = translateRepository.findByWord(text);
        if (translatedText == null) {
            translatedText = googleTranslatorApiClient.translate(sourceLang, targetLang, text);
            translateRepository.save(text, translatedText, sourceLang, targetLang);
        }
        LOGGER.log(Level.INFO, "Translated text: {0}", translatedText);
        return translatedText;
    }

}
