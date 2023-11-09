package com.example.translate.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.translate.client.GoogleTranslatorApiClient;
import com.example.translate.dto.CreateTranslationDto;
import com.example.translate.entity.Translations;
import com.example.translate.exceptions.NotFoundException;
import com.example.translate.repository.TranslationsRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
        String translatedTxt = googleClient.translate(sourceLang, targetLang, word );
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
    public Optional<Translations> findBySourceTextAndTargetLanguage(String word, String targetLang) {
        return repository.findBySourceTextAndTargetLanguage(word, targetLang);
        
    }

    @Override
    public List<Translations> findAllByTargetLanguage(String targetLang) {
        List<Translations> trg = repository.findAllByTargetLanguage(targetLang);
        if (trg.isEmpty()) {
            throw new NotFoundException("no result found for given parametrs");
        }
        return trg;
    }

    @Override
    public List<Translations> findAllBySourceLanguage(String sourceLang) {
        List<Translations> srg = repository.findAllBySourceLanguage(sourceLang);
        if (srg.isEmpty()) {
            throw new NotFoundException("no result found for given parametrs");
        }
        return srg;
    }

    @Override
    public List<Translations> findAllBySourceLanguageAndTargetLanguage(String sourceLang, String targetLang) {
        List<Translations> translations = repository.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang);
        if (translations.isEmpty()) {
            throw new NotFoundException("no result found for given parametrs");
        }
        return translations;
    }

    @Transactional
    @Override
    public void deleteBySourceText(String sourceText) {
        repository.deleteBySourceText(sourceText);
    }

    @Override
    public void saveData(CreateTranslationDto newTranslation){
        Translations data = new Translations();
        data.setSourceLanguage(newTranslation.getSourceLang());
        data.setTargetLanguage(newTranslation.getTargetLang());
        data.setSourceText(newTranslation.getSourceText());
        data.setTranslatedText(newTranslation.getTranslatedText());
        repository.save(data);
        
    }

    @Override
    public Translations updateData(Long id, String sourceLang, String sourceText, String targetLang, String translatedText) {
        Optional<Translations> existingData =repository.findById(id);
        if (existingData.isPresent()) {
            Translations data = existingData.get();

            if (sourceLang != null) {
                data.setSourceLanguage(sourceLang);
            }

            if (targetLang != null) {
                data.setTargetLanguage(targetLang);
            }

            if (sourceText != null) {
                data.setSourceText(sourceText);
            }

            if (translatedText != null) {
                data.setTranslatedText(translatedText);
            }

            return repository.save(data);
        } else {
            throw new NotFoundException("Data with ID " + id + " not found");
        }
    }

}
