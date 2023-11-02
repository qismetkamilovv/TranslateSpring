package com.example.translate.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public Translations findBysourceText(@PathVariable String sourceText) {
        return service.findBySourceText(sourceText);
    }

    @GetMapping("getTarg/{targetLang}")
    public List<Translations> findAllByTargetLanguage(@PathVariable String targetLang){
        return service.findAllByTargetLanguage(targetLang);
    }

    @GetMapping("getSour/{sourceLang}")
    public List<Translations> findAllBySourceLanguage(@PathVariable String sourceLang ){
        return service.findAllBySourceLanguage(sourceLang); 
    }

    @GetMapping("get/sourceLang/targetLang")
    public List<Translations> findByAllSourceLangandTargetLang(@RequestParam("source") String sourceLang,
        @RequestParam("target") String targetLang){
        return service.findAllBySourceLanguageAndTargetLanguage(sourceLang,targetLang);
    }

    @DeleteMapping("delete/{sourceText}")
    public ResponseEntity<String> deleteBySourceText(@PathVariable String sourceText) {
        service.deleteBySourceText(sourceText);
        return ResponseEntity.ok().build();
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
    public String translate(@RequestParam("source") String sourceLang,
        @RequestParam String text,
        @RequestParam("target") String targetLang){
        return service.translate(sourceLang, text, targetLang);
    }
    
}
 