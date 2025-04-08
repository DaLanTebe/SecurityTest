package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Указывает, что это репозиторий
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Метод для поиска пользователя по имени
}