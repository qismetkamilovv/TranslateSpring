package com.example.translate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.translate.dto.LoginRequest;
import com.example.translate.dto.RegisterRequest;
import com.example.translate.dto.Response;
import com.example.translate.entity.User;
import com.example.translate.service.AuthenticationService;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authService;

    @PostMapping("register")
    public ResponseEntity signUp(@RequestBody RegisterRequest request) {
        Response response = authService.register(request);
        return ResponseEntity.ok(response);

    }
    @PostMapping("login")
    public ResponseEntity signIn(@RequestBody LoginRequest request ) {
        Response response = authService.login(request);
        return ResponseEntity.ok(response);

    }

}
