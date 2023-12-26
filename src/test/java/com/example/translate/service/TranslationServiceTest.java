package com.example.translate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.translate.dto.CreateTranslationDto;
import com.example.translate.entity.Translations;
import com.example.translate.exceptions.NotFoundException;
import com.example.translate.repository.TranslationsRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TranslationServiceTest {

    @Autowired
    private TranslationService translationService;

    @MockBean
    private TranslationsRepository translationsRepository;

    @Test
    public void shouldReturnList_whenFindBySourceText_givenSourceTextExist() {
        // given
        String sourceText = "cut";
        Translations translation = new Translations();
        translation.setSourceText(sourceText);
        translation.setTranslatedText("kesmek");
        translation.setSourceLanguage("en");
        translation.setTargetLanguage("az");
        List<Translations> testData = Collections.singletonList(translation);
        when(translationsRepository.findBySourceText(sourceText)).thenReturn(testData);

        // when
        List<Translations> result = translationService.findBySourceText(sourceText);

        // then
        verify(translationsRepository, times(1)).findBySourceText(sourceText);
        assertFalse(result.isEmpty());
        assertEquals(testData, result);
    }

    @Test
    public void shouldThrowNotFoundException_whenFindBySourceText_givenNoSourceTextExist() {
        // given
        String sourceText = "cut";
        when(translationsRepository.findBySourceText(sourceText)).thenReturn(Collections.emptyList());

        // when
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> translationService.findBySourceText(sourceText));

        // then
        verify(translationsRepository, times(1)).findBySourceText(sourceText);
        assertEquals("there is no word in the database similar this", ex.getMessage());

    }

    @Test
    public void shouldAllTranslates_whenGetAll() {
        // given
        Translations translation = new Translations();
        translation.setSourceText("cut");
        translation.setTranslatedText("kesmek");
        translation.setSourceLanguage("en");
        translation.setTargetLanguage("az");
        List<Translations> getall = Collections.singletonList(translation);
        when(translationsRepository.findAll()).thenReturn(getall);
        List<Translations> expected = Collections.singletonList(translation);

        // when
        List<Translations> actual = translationService.getAll();

        // then
        Translations actualTranslation = actual.get(0);
        verify(translationsRepository, times(1)).findAll();
        assertEquals(expected, actual);
        assertEquals(1, actual.size());
        assertEquals("cut", actualTranslation.getSourceText());
    }

    @Test
    public void shouldThrowNotFoundException_whenGetAllIsEmpty() {
        // given
        when(translationsRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        NotFoundException ex = assertThrows(NotFoundException.class, () -> translationService.getAll());

        // then
        verify(translationsRepository, times(1)).findAll();
        assertEquals("there is no word database", ex.getMessage());
    }

    @Test
    public void shouldReturnAllTargetLanguages_whenFindByTargetLanguage_givenTargetLanguage() {
        // given
        String targetlanguage = "ru";
        Translations targ = new Translations();
        targ.setSourceText("cut");
        targ.setTranslatedText("kesmek");
        targ.setSourceLanguage("en");
        targ.setTargetLanguage("ru");
        List<Translations> data = Collections.singletonList(targ);
        when(translationsRepository.findAllByTargetLanguage(targetlanguage)).thenReturn(data);
        List<Translations> expected = Collections.singletonList(targ);

        // when
        List<Translations> actual = translationService.findAllByTargetLanguage(targetlanguage);

        // then
        verify(translationsRepository, times(1)).findAllByTargetLanguage(targetlanguage);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundException_whenFindByTargetLanguageIsEmpty() {
        // given
        String targetlang = "ru";
        when(translationsRepository.findAllByTargetLanguage(targetlang)).thenReturn(Collections.emptyList());

        // when
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> translationService.findAllByTargetLanguage(targetlang));

        // then
        verify(translationsRepository, times(1)).findAllByTargetLanguage(targetlang);
        assertEquals("no result found for given parametrs", ex.getMessage());
    }

    @Test
    public void shouldReturnTargetLanguageAndSourceLanguage_whenfindAllBySourceLanguageAndTargetLanguage_givenTargetAndSourceLang() {
        // given
        String targetLang = "az";
        String sourceLang = "en";
        Translations translations = new Translations();
        translations.setSourceText("cut");
        translations.setTranslatedText("kesmek");
        translations.setSourceLanguage("en");
        translations.setTargetLanguage("az");
        List<Translations> data = Collections.singletonList(translations);
        when(translationsRepository.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang)).thenReturn(data);

        // when
        List<Translations> result = translationService.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang);

        // then
        verify(translationsRepository, times(1)).findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang);
        assertEquals(data, result);
    }

    @Test
    public void shouldThrowsNotfoundException_whenSourceAndTargetLanguageIsEmpty() {
        // given
        String targetLang = "az";
        String sourceLang = "en";
        when(translationsRepository.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang))
                .thenReturn(Collections.emptyList());

        // when
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> translationService.findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang));

        // then
        verify(translationsRepository, times(1)).findAllBySourceLanguageAndTargetLanguage(sourceLang, targetLang);
        assertEquals("no result found for given parametrs", ex.getMessage());

    }

    @Test
    public void shouldSaveData_whenSaveData_givenAllInformations() {
        // given
        CreateTranslationDto translationDto = new CreateTranslationDto();
        translationDto.setSourceLang("en");
        translationDto.setSourceText("mouse");
        translationDto.setTargetLang("az");
        translationDto.setTranslatedText("sıçan");

        Translations translations = new Translations();
        translations.setSourceLanguage(translationDto.getSourceLang());
        translations.setSourceText(translationDto.getSourceText());
        translations.setTargetLanguage(translationDto.getTargetLang());
        translations.setTranslatedText(translationDto.getTranslatedText());

        when(translationsRepository.save(any(Translations.class))).thenReturn(translations);

        // when
        translationService.saveData(translationDto);

        // then
        verify(translationsRepository, times(1)).save(any(Translations.class));
        assertEquals(translationDto.getSourceLang(), translations.getSourceLanguage());
        assertEquals(translationDto.getTargetLang(), translations.getTargetLanguage());
        assertEquals(translationDto.getSourceText(), translations.getSourceText());
        assertEquals(translationDto.getTranslatedText(), translations.getTranslatedText());

    }
}
