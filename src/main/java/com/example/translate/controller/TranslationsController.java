package com.example.translate.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.translate.dto.CreateResponse;
import com.example.translate.dto.CreateTranslationDto;
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
    public List<Translations> findBysourceText(@PathVariable String sourceText) {
        return service.findBySourceText(sourceText);
    }

    @GetMapping("getTarg/{targetLang}")
    public List<Translations> findAllByTargetLanguage(@PathVariable String targetLang) {
        return service.findAllByTargetLanguage(targetLang);
    }

    @GetMapping("getSour/{sourceLang}")
    public List<Translations> findAllBySourceLanguage(@PathVariable String sourceLang) {
        return service.findAllBySourceLanguage(sourceLang);
    }

    @GetMapping("get/sourceLang/targetLang")
    public List<Translations> findByAllSourceLangandTargetLang(@RequestParam("source") String sourceLang,
            @RequestParam("target") String targetLang) {
        return service.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang);
    }

    @DeleteMapping("delete/{sourceText}")
    public ResponseEntity<String> deleteBySourceText(@PathVariable String sourceText) {
        service.deleteBySourceText(sourceText);
        return ResponseEntity.ok().build();
    }

    @PostMapping("translate")
    public CreateResponse translate(@RequestParam String text) {
        return service.translate(text);
    }

    @PostMapping("translate/withTarget")
    public CreateResponse translate(@RequestParam String text,
            @RequestParam("target") String targetLang) {
        return service.translate(text, targetLang);
    }

    @PostMapping("translate/withSource")
    public CreateResponse translate(@RequestParam("source") String sourceLang,
            @RequestParam String text,
            @RequestParam("target") String targetLang) {
        return service.translate(sourceLang, text, targetLang);
    }

    @PostMapping(value = "save", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> saveData(
            @RequestBody CreateTranslationDto newTranslation) {
        service.saveData(newTranslation);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Translations> updateData(@PathVariable Long id,
            @RequestBody CreateTranslationDto dto) {
        Translations updatedData = service.updateData(id, dto);
        return ResponseEntity.ok(updatedData);
    }

    
}
