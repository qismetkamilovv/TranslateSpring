package com.example.translate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.translate.entity.Translations;
import com.example.translate.repository.TranslationsRepository;

// TODO implment TranslateService interface and implement all methods of this interface using Spring JPA/DATA
@Service
public class TranslationsService implements TranslateService {

   
    private final TranslationsRepository translationsRepository;

    private final RestTemplate restTemplate;
    @Autowired
    public TranslationsService(RestTemplate restTemplate, TranslationsRepository translationsRepository) {
        this.restTemplate = restTemplate;
        this.translationsRepository = translationsRepository ;
    }
    public void postData(String data){
        String url = "ya29.a0AfB_byApwyVDWxDt0KTee4u-FmPX9Fds1qhqnvTOgibQD-4KmZuKIG4mLkVmFIF2mdf_P13PFmQJ1FOwnV5SQ-fcg9oEAgBPpGrCAaCXNVHwGf9DYMMRw6cmBy1wtwn5qREtS3ZTdVsDVAdNgBfVtBvp5Ag53MJ6iDfePNlKMo8TaCgYKAZ4SARESFQGOcNnChyb_uOzdROdiW8_YzlfWIA0179" ;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity( url, data, String.class) ;
            int statusCode= response.getStatusCodeValue();
            if (statusCode >= 200 && statusCode < 300) {
                
                String responseBody = response.getBody();
                System.out.println("POST succed, output: " + responseBody);
            } else {
                System.out.println("POST unsucced, error code: " + statusCode);
            }
        
        } catch (HttpClientErrorException e) {
            
            System.out.println("POST unsucced, error code: " + e.getRawStatusCode());
        }

    }
    @Override
    public String translate(String word) {
        return null;
    }
    @Override
    public String translate(String word, String targetLang) {
        return null ;
    }
    @Override
    public String translate(String word, String sourceLang, String targetLang) {
        return null ;
    }
    

    // public String FetchDataFromApi() {
    //     String url = "ya29.a0AfB_byCoz83ZNrjGThajVJUOGW1NBqic6T5GgAX5p3N8myUjpnZpOEDTUeyU1DXz2qAreYT1YKeFuwxStluGyttRObOpaxmc96fOoxex4SQcVOOBo6zWduxcAM8HzAE7YyTKNRd9cm4aa1M28KmGn4j7PYEKqtwda9UHN1kfE92maCgYKAU8SARESFQGOcNnCLRo9uZNdLAQAZZcddtCGVQ0179";
    //     return restTemplate.getForObject(url, String.class);
    // }

    // public List<Translations> getAll() {
    //     return translationsRepository.findAll();
    // }

    public Translations getBysourceText(@PathVariable String sourceText) {
        return translationsRepository.findBysourceText(sourceText).orElse(null);
    }

    
}

