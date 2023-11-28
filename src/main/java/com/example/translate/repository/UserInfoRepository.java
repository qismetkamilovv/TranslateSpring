package com.example.translate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.translate.entity.UserInfo;


public interface UserInfoRepository extends JpaRepository<UserInfo ,Long> {

    Optional<UserInfo> findByfirstname(String username);
    

}
