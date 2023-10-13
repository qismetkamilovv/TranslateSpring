package com.example.translate.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.translate.entity.Translations;
import com.example.translate.repository.TranslationsRepository;

// TODO implment TranslateService interface and implement all methods of this interface using Spring JPA/DATA
@Service
public class TranslationsService {

   
    private final TranslationsRepository translationsRepository;

    private final RestTemplate restTemplate;

    public TranslationsService(RestTemplate restTemplate, TranslationsRepository translationsRepository) {
        this.restTemplate = restTemplate;
        this.translationsRepository = translationsRepository ;
    }
    public void postData(String data){
        String url = "ya29.a0AfB_byDvRUrXXF3jk2RkZVnRJmgiQXsYiempO03_O-RagRJQKDkxgKA_4QZr-sEz8udSH3vB9iK7j4QCeDDwH5wRNo2yb44B0HbLCuCZuRhEQ4iQPMkT5jd5Z88sRXL1pHTfHcJdG9ELleXh8cUvMyy3bg47Gc702Fn9OM0UQjgpaCgYKAQMSARESFQGOcNnC0t4KmUIJP38vzEbWO6rjrg0179" ;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity( url, data, String.class) ;
            int statusCode= response.getStatusCodeValue();
            if (statusCode >= 200 && statusCode < 300) {
                // Başarılı yanıt
                String responseBody = response.getBody();
                System.out.println("POST succed, output: " + responseBody);
            } else {
                // Başarısız yanıt
                System.out.println("POST unsucced, error code: " + statusCode);
            }
        
        } catch (HttpClientErrorException e) {
            
            System.out.println("POST unsucced, error code: " + e.getRawStatusCode());
        }

        }
    

    // public String FetchDataFromApi() {
    //     String url = "ya29.a0AfB_byCoz83ZNrjGThajVJUOGW1NBqic6T5GgAX5p3N8myUjpnZpOEDTUeyU1DXz2qAreYT1YKeFuwxStluGyttRObOpaxmc96fOoxex4SQcVOOBo6zWduxcAM8HzAE7YyTKNRd9cm4aa1M28KmGn4j7PYEKqtwda9UHN1kfE92maCgYKAU8SARESFQGOcNnCLRo9uZNdLAQAZZcddtCGVQ0179";
    //     return restTemplate.getForObject(url, String.class);
    // }

    // public List<Translations> getAll() {
    //     return translationsRepository.findAll();
    // }

    // public Translations getBysourceText(@PathVariable String sourceText) {
    //     return translationsRepository.findBysourceText(sourceText).orElse(null);

    // }
}
