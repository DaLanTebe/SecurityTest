package com.example.demo.controller;

import com.example.demo.model.User; // Импортируем модель пользователя
import com.example.demo.service.UserService; // Импортируем сервис пользователя
import com.example.demo.dto.RegisterRequest; // Импортируем DTO для регистрации
import org.springframework.beans.factory.annotation.Autowired; // Импортируем аннотацию для внедрения зависимостей
import org.springframework.http.HttpStatus; // Импортируем класс для статусов HTTP
import org.springframework.http.ResponseEntity; // Импортируем класс для создания ответов
import org.springframework.web.bind.annotation.*; // Импортируем аннотации для работы с REST-контроллерами

@RestController
@RequestMapping("/api/auth")
public class UserController {

}
