package com.example.translate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.translate.entity.User;


public interface UserRepository extends JpaRepository<User ,Long> {

    Optional<User> findByfirstname(String username);

    User findByMail(String email);
    

}
