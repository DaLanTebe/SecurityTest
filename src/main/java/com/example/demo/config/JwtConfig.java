package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt") // Будет брать настройки из application.properties
public class JwtConfig {
    private String secret;      // Секретный ключ (например, "mySuperSecretKey123!")
    private long expirationMs;  // Время жизни токена (например, 86400000 мс = 1 день)
}
