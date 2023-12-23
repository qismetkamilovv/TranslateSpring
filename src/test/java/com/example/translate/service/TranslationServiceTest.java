package com.example.translate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
}
