package com.example.translate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationsRepository extends JpaRepository<Translations ,Long > {

    
    public String  findBysourceText(String sourceText) ;
    public <list> void getAll() ;
    
}
