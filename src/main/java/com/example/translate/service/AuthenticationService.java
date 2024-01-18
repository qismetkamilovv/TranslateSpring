package com.example.translate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.translate.dto.LoginRequest;
import com.example.translate.dto.RegisterRequest;
import com.example.translate.dto.Response;
import com.example.translate.entity.User;
import com.example.translate.repository.UserRepository;
import com.example.translate.security.JwtService;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public Response register(RegisterRequest request) {
        User user = new User();
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setMail(request.getEmail());
        repository.save(user);

        String token = jwtService.generateToken(user);

        return new Response(200, "ok", token);

    }

    public Response login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByMail(request.getEmail());
        String token = jwtService.generateToken(user);
        return new Response(200, "ok", token);

    }
}
