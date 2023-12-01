package com.example.translate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.translate.entity.UserInfo;
import com.example.translate.repository.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    
    @Autowired
    private UserInfoRepository repositoryInfo ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repositoryInfo.save(userInfo);
        return "user added to system" ;
    }
}
