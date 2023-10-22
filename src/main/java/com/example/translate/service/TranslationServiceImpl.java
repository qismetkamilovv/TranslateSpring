package com.example.translate.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.translate.client.GoogleTranslatorApiClient;
import com.example.translate.entity.Translations;
import com.example.translate.repository.TranslationsRepository;

@Service
public class TranslationServiceImpl implements TranslationService {

    public final static String SOURCE_LANG = "en";
    public final static String TARGET_LANG = "az";

    private final GoogleTranslatorApiClient googleClient;
    private final TranslationsRepository repository;

    public TranslationServiceImpl(GoogleTranslatorApiClient googleClient,
            TranslationsRepository translationsRepository) {
        this.repository = translationsRepository;
        this.googleClient = googleClient;
    }

    @Override
    public String translate(String text) {
        Optional<Translations> opt = repository.findBySourceText(text);
        if (opt.isPresent()) {
            return opt.get().getTranslatedText();
        }
        String translatedTxt = googleClient.translate(SOURCE_LANG, TARGET_LANG, text) ;
        Translations translations = new Translations();
        translations.setSourceLanguage(SOURCE_LANG);
        translations.setTargetLanguage(TARGET_LANG);
        translations.setSourceText(text) ;
        translations.setTranslatedText(translatedTxt);
        repository.save(translations);
        return translatedTxt ;
    }

    @Override
    public String translate(String word, String targetLang) {
        Optional<Translations> trs = repository.findBySourceText(word) ;
        if(trs.isPresent()){
            return trs.get().getTranslatedText();
        }
        String translatedTxt = googleClient.translate(SOURCE_LANG, targetLang, word);
        Translations translations = new Translations();
        translations.setSourceLanguage(SOURCE_LANG);
        translations.setTargetLanguage(targetLang);
        translations.setSourceText(word);
        translations.setTranslatedText(translatedTxt);
        repository.save(translations);
        return translatedTxt;
    }

    @Override
    public String translate(String sourceLang,String word , String targetLang) {
        Optional<Translations> trs = repository.findBySourceTextAndTargetLang(word,targetLang) ;
        if(trs.isPresent()){
            return trs.get().getTranslatedText();
        }
        String translatedTxt = googleClient.translate(sourceLang,word , targetLang);
        Translations translations = new Translations();
        translations.setSourceLanguage(sourceLang);
        translations.setSourceText(word);
        translations.setTargetLanguage(targetLang);
        translations.setTranslatedText(translatedTxt);
        repository.save(translations);
        return translatedTxt;
    }

    public List<Translations> getAll() {
        return repository.findAll();
    }

    public Translations getBySourceText(String sourceText) {
        return repository.findBySourceText(sourceText).orElse(null);
    }

    @Override
    public Translations translationSearch(String sourceLang, String targetLang) {
        return repository.translationSearch(sourceLang, targetLang);
    }

    @Override
    public Optional<Translations> findBySourceTextAndTargetLang(String sourceText, String targetLanguage) {
        return repository.findBySourceTextAndTargetLang(targetLanguage, targetLanguage);
    }

}
