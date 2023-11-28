package com.example.translate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.translate.client.GoogleTranslatorApiClient;
import com.example.translate.dto.CreateResponse;
import com.example.translate.dto.CreateTranslationDto;
import com.example.translate.entity.Translations;
import com.example.translate.entity.UserInfo;
import com.example.translate.exceptions.NotFoundException;
import com.example.translate.repository.TranslationsRepository;
import com.example.translate.repository.UserInfoRepository;

@Service
public class TranslationServiceImpl implements TranslationService {

    public final static String SOURCE_LANG = "en";
    public final static String TARGET_LANG = "az";

    private final GoogleTranslatorApiClient googleClient;
    private final TranslationsRepository repository;

    @Autowired
    private UserInfoRepository repositoryInfo ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    public TranslationServiceImpl(GoogleTranslatorApiClient googleClient,
            TranslationsRepository translationsRepository) {
        this.repository = translationsRepository;
        this.googleClient = googleClient;
    }

    @Override
    public CreateResponse translate(String text) {
        return this.translate(SOURCE_LANG, text, TARGET_LANG);

    }

    @Override
    public CreateResponse translate(String word, String targetLang) {
        return this.translate(SOURCE_LANG, word, targetLang);
    }

    @Override
    public CreateResponse translate(String sourceLang, String word, String targetLang) {

        return repository.findBySourceTextAndTargetLanguage(word, targetLang)
                .map((Translations t) -> {
                    CreateResponse response = new CreateResponse(t);
                    return response;
                }).orElseGet(() -> {
                    String translatedTxt = googleClient.translate(sourceLang, targetLang, word);
                    Long id = save(sourceLang, targetLang, word, translatedTxt);
                    CreateResponse response = new CreateResponse(id, translatedTxt);
                    return response;
                });
    }

    private Long save(String sLang, String tLang, String text, String trsText) {
        Translations translations = new Translations();
        translations.setSourceLanguage(sLang);
        translations.setSourceText(text);
        translations.setTargetLanguage(tLang);
        translations.setTranslatedText(trsText);

        repository.save(translations);

        return translations.getId();
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
    public void saveData(CreateTranslationDto newTranslation) {
        Translations data = new Translations();
        data.setSourceLanguage(newTranslation.getSourceLang());
        data.setTargetLanguage(newTranslation.getTargetLang());
        data.setSourceText(newTranslation.getSourceText());
        data.setTranslatedText(newTranslation.getTranslatedText());
        repository.save(data);

    }

    @Override
    public Translations updateData(Long id, CreateTranslationDto dto) {
        Optional<Translations> existingData = repository.findById(id);

        if (existingData.isPresent()) {
            Translations data = existingData.get();

            if (StringUtils.hasText(dto.getSourceLang())) {
                data.setSourceLanguage(dto.getSourceLang());
            }

            if (StringUtils.hasText(dto.getTargetLang())) {
                data.setTargetLanguage(dto.getTargetLang());
            }

            if (StringUtils.hasText(dto.getSourceText())) {
                data.setSourceText(dto.getSourceText());
            }

            if (StringUtils.hasText(dto.getTranslatedText())) {
                data.setTranslatedText(dto.getTranslatedText());
            }

            return repository.save(data);
        } else {
            throw new NotFoundException("Data with ID " + id + " not found");
        }
    }

    @Override
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repositoryInfo.save(userInfo);
        return "user added to system" ;
    }

}
