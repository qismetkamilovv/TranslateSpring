package com.example.translate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TranslationsController {
    @Autowired
    private TranslationsRepository translationsRepository;

    @GetMapping
    public List<Translations> getAll() {
        return translationsRepository.findAll();
    }

    @GetMapping("/{source_text}")
    public Translations getsBysourceText(@PathVariable String sourceText) {
        return translationsRepository.findBysourceText(sourceText).orElse(null);
    }
}
