package com.example.translate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.translate.client.GoogleTranslatorApiClient;
import com.example.translate.entity.Translations;
import com.example.translate.repository.TranslationsRepository;

@Service
public class TranslationServiceImpl implements TranslationService {

    public final static String SOURCE_LANG = "en";
    public final static String TARGET_LANG = "az";

    private final GoogleTranslatorApiClient googleClient ;
    private final TranslationsRepository repository;
    private final RestTemplate restTemplate;

    public TranslationServiceImpl(GoogleTranslatorApiClient googleClient, RestTemplate restTemplate, TranslationsRepository translationsRepository) {
        this.restTemplate = restTemplate;
        this.repository = translationsRepository;
        this.googleClient = googleClient ;
    }

    @Override
    public String translate(String text) {
        repository.findBySourceText(text).map(t->t.getTranslated_text()).orElseGet(()->{
            
            return null ;
        });
        return null;
    }

    @Override
    public String translate(String word, String targetLang) {
        return null;
    }

    @Override
    public String translate(String word, String sourceLang, String targetLang) {
        return null;
    }

    // public String FetchDataFromApi() {
    // String url =
    // "ya29.a0AfB_byCoz83ZNrjGThajVJUOGW1NBqic6T5GgAX5p3N8myUjpnZpOEDTUeyU1DXz2qAreYT1YKeFuwxStluGyttRObOpaxmc96fOoxex4SQcVOOBo6zWduxcAM8HzAE7YyTKNRd9cm4aa1M28KmGn4j7PYEKqtwda9UHN1kfE92maCgYKAU8SARESFQGOcNnCLRo9uZNdLAQAZZcddtCGVQ0179";
    // return restTemplate.getForObject(url, String.class);
    // }

    public List<Translations> getAll() {
    return repository.findAll();
    }

    public Translations getBySourceText(String sourceText) {
        return repository.findBySourceText(sourceText).orElse(null);
    }

}
