package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Указывает, что это контроллер REST
@RequestMapping("/auth") // Базовый URL для всех эндпоинтов в этом контроллере
public class AuthController {

    private final UserService userService;

    @Autowired // Внедрение зависимостей через конструктор
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Эндпоинт для регистрации нового пользователя
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        User registeredUser  = userService.registerUser(request);
        return ResponseEntity.ok("User  registered successfully: " + registeredUser .getUsername()); // Возвращает статус 201 Created
    }

    // Эндпоинт для аутентификации
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
