package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.dto.LoginRequest;
import com.vidaplus.sghss.dto.RegisterRequest;
import com.vidaplus.sghss.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request.getNome(), request.getEmail(), request.getSenha(), request.getTipo());
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(token);
    }
}
