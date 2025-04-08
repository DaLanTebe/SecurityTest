package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "roles") // Имя таблицы в базе данных
@Data // Генерирует геттеры, сеттеры, toString и другие методы
@NoArgsConstructor // Генерирует конструктор без параметров
@AllArgsConstructor // Генерирует конструктор со всеми параметрами
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент для ID
    private Long id;

    @Column(nullable = false, unique = true) // Уникальное имя роли
    private String name;

    @ManyToMany(mappedBy = "roles") // Связь с пользователями
    private Set<User> users; // Пользователи, имеющие эту роль
}
