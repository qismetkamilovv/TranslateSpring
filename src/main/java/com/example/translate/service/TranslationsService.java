package com.example.translate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.translate.TranslationsRepository;
import com.example.translate.entity.Translations;

// TODO implment TranslateService interface and implement all methods of this interface using Spring JPA/DATA
@Service
public class TranslationsService {

    @Autowired
    private TranslationsRepository translationsRepository ;

    public List<Translations> getAll(){
        return translationsRepository.findAll();
    }


    public Translations getBysourceText(@PathVariable String sourceText){
        return translationsRepository.findBysourceText(sourceText).orElse(null) ;
    }
}
