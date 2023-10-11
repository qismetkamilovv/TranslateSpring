package com.example.translate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.translate.entity.Translations;

@RestController
@RequestMapping
public class TranslationsController {

    // HERE autowire service class now repository
    @Autowired
    private TranslationsRepository translationsRepository;

    @GetMapping
    public List<Translations> getAll() {
        return translationsRepository.findAll();
    }

    @GetMapping("/{source_text}")
    public Translations getsBysourceText(@PathVariable String sourceText) {
        // Here what you can do is actually throw an exception using .orElseThrow() or
        // annother object in .orElse()
        // TODO read about Optional class, what it does and how can you use it.
        return translationsRepository.findBysourceText(sourceText).orElse(null);
    }
}
