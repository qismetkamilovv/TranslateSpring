package com.example.translate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.translate.entity.UserInfo;
import com.example.translate.service.UserInfoService;

@RestController
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService ;

    
    public void signUp(){
        //TODO implement me
    }

    public void signIn(){
        //TODO implement me
    }

    @PostMapping(value = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String addNewuser (@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }
}
