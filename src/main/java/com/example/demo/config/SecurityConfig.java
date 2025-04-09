package com.example.demo.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.JwtAuthFilter; // Импортируем наш фильтр
import org.springframework.beans.factory.annotation.Autowired; // Импортируем аннотацию для внедрения зависимостей
import org.springframework.context.annotation.Bean; // Импортируем аннотацию для определения бинов
import org.springframework.context.annotation.Configuration; // Импортируем аннотацию для конфигурации
import org.springframework.security.authentication.AuthenticationManager; // Импортируем интерфейс для управления аутентификацией
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; // Импортируем класс для настройки менеджера аутентификации
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Импортируем класс для настройки безопасности HTTP
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Импортируем аннотацию для включения конфигурации безопасности
import org.springframework.security.config.http.SessionCreationPolicy; // Импортируем класс для управления сессиями
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Импортируем класс для шифрования паролей
import org.springframework.security.crypto.password.PasswordEncoder; // Импортируем интерфейс для шифрования паролей
import org.springframework.security.web.SecurityFilterChain; // Импортируем класс для цепочки фильтров
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Импортируем класс для аутентификации по имени пользователя и паролю

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean// Определяем бин для менеджера аутентификации
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        return authManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/auth/register", "/auth/login").permitAll())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .build();
    }
}
