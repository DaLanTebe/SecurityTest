package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users") // Имя таблицы в базе данных
@Data // Генерирует геттеры, сеттеры, toString и другие методы
@NoArgsConstructor // Генерирует конструктор без параметров
@AllArgsConstructor // Генерирует конструктор со всеми параметрами
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент для ID
    private Long id;

    @Column(nullable = false, unique = true) // Уникальное имя пользователя
    private String username;

    @Column(nullable = false) // Пароль не может быть null
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // Связь многие-ко-многим с ролями
    @JoinTable(
            name = "user_roles", // Имя таблицы для связи
            joinColumns = @JoinColumn(name = "user_id"), // Внешний ключ для пользователя
            inverseJoinColumns = @JoinColumn(name = "role_id") // Внешний ключ для роли
    )
    private Set<Role> roles; // Роли пользователя
}