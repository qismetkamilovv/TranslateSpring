package com.example.translate.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.translate.entity.Translations;
import com.example.translate.service.TranslationService;

@RestController
@RequestMapping
public class TranslationsController {

    private TranslationService service;

    public TranslationsController(TranslationService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<Translations> getAll() {
        return service.getAll();
    }

    @GetMapping("get/{sourceText}")
    public Translations getsBysourceText(@PathVariable String sourceText) {
        return service.getBySourceText(sourceText);
    }

    @GetMapping("get")
    public Translations translationSearch(@RequestParam("source") String sourceLang,
    @RequestParam("target") String targetLang){
        return service.translationSearch(targetLang, targetLang);
    }

    @PostMapping("translate")
    public String translate(@RequestParam String text){
        return service.translate(text);
    }

    @PostMapping("translate/withTarget")
    public String translate(@RequestParam String text,@RequestParam("target") String targetLang ){
        return service.translate(text, targetLang);
    }

    @PostMapping("translate/withSource")
    public String translate(@RequestParam String text,
    @RequestParam("target") String targetLang,
    @RequestParam("source") String sourceLang){
        return service.translate(text, targetLang, sourceLang);
    }
    
}
 