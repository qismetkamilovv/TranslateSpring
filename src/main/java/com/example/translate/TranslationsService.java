package com.example.translate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TranslationsService {
    @Autowired
    private TranslationsRepository translationsRepository ;

    public List<Translations>getAll(){
        return translationsRepository.findAll();
    }


    
    public Translations getBysourceText(@PathVariable String sourceText){
        return translationsRepository.findBysourceText(sourceText).orElse(null) ;
    }
}
