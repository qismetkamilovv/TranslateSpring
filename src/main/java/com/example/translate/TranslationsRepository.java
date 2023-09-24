package com.example.translate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationsRepository extends JpaRepository<Translations ,Long > {

    
    public Optional<Translations>  findBysourceText(String sourceText) ;
    
}
