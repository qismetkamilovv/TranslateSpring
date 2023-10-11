package com.example.translate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.translate.TranslationsRepository;
import com.example.translate.entity.Translations;

// TODO implment TranslateService interface and implement all methods of this interface using Spring JPA/DATA
@Service
public class TranslationsService {

   
    private final TranslationsRepository translationsRepository;

    private final RestTemplate restTemplate;

    public TranslationsService(RestTemplate restTemplate, TranslationsRepository translationsRepository) {
        this.restTemplate = restTemplate;
        this.translationsRepository = translationsRepository ;
    }

    public String FetchDataFromApi() {
        String url = "ya29.a0AfB_byCoz83ZNrjGThajVJUOGW1NBqic6T5GgAX5p3N8myUjpnZpOEDTUeyU1DXz2qAreYT1YKeFuwxStluGyttRObOpaxmc96fOoxex4SQcVOOBo6zWduxcAM8HzAE7YyTKNRd9cm4aa1M28KmGn4j7PYEKqtwda9UHN1kfE92maCgYKAU8SARESFQGOcNnCLRo9uZNdLAQAZZcddtCGVQ0179";
        return restTemplate.getForObject(url, String.class);
    }

    public List<Translations> getAll() {
        return translationsRepository.findAll();
    }

    public Translations getBysourceText(@PathVariable String sourceText) {
        return translationsRepository.findBysourceText(sourceText).orElse(null);

    }
}
