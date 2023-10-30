package com.example.translate.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.translate.client.GoogleTranslatorApiClient;
import com.example.translate.entity.Translations;
import com.example.translate.exceptions.NotFoundException;
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
        return this.translate(SOURCE_LANG, text, TARGET_LANG);

    }

    @Override
    public String translate(String word, String targetLang) {
        return this.translate(SOURCE_LANG, word, targetLang);
    }

    @Override
    public String translate(String sourceLang, String word, String targetLang) {
        Optional<Translations> trs = repository.findBySourceTextAndTargetLanguage(word, targetLang);
        if (trs.isPresent()) {
            return trs.get().getTranslatedText();
        }
        String translatedTxt = googleClient.translate(sourceLang, word, targetLang);
        Translations translations = new Translations();
        translations.setSourceLanguage(sourceLang);
        translations.setSourceText(word);
        translations.setTargetLanguage(targetLang);
        translations.setTranslatedText(translatedTxt);
        repository.save(translations);
        return translatedTxt;
    }

    public List<Translations> getAll() {
        List<Translations> translations = repository.findAll();
        if (translations.isEmpty()) {

            throw new NotFoundException("there is no word database");
        }
        return translations;
    }

    public Translations findBySourceText(String sourceText) {
        return repository.findBySourceText(sourceText).orElseThrow(NotFoundException::new);
    }

    @Override
    public Translations findBySourceTextAndTargetLanguage(String word, String targetLang) {
        return repository.findBySourceTextAndTargetLanguage(word, targetLang)
        .orElseThrow(()->new NotFoundException("no result found for given parametrs"));
    }

    @Override
    public List<Translations> findAllByTargetLanguage(String targetLang) {
        return repository.findAllByTargetLanguage(targetLang);
    }

    @Override
    public List<Translations> findAllBySourceLanguage(String sourceLang) {
        return repository.findAllBySourceLanguage(sourceLang);
    }

    @Override
    public List<Translations> findAllBySourceLanguageAndTargetLanguage(String sourceLang, String targetLang) {
       List <Translations> translations = repository.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang);
       if (translations.isEmpty()) {

        throw new NotFoundException("no result found for given parametrs");
        
       }
       return translations ;
    }

}
