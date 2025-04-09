package com.example.demo.security;

import com.example.demo.model.User; // Импортируем модель пользователя
import com.example.demo.repository.UserRepository; // Импортируем репозиторий для работы с пользователями
import org.springframework.beans.factory.annotation.Autowired; // Импортируем аннотацию для внедрения зависимостей
import org.springframework.security.core.userdetails.UserDetails; // Импортируем интерфейс UserDetails
import org.springframework.security.core.userdetails.UserDetailsService; // Импортируем интерфейс UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Импортируем исключение для обработки отсутствия пользователя
import org.springframework.stereotype.Service; // Импортируем аннотацию для определения сервиса

@Service // Указываем, что этот класс является сервисом
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository; // Репозиторий для работы с пользователями

    @Autowired // Внедряем зависимость UserRepository
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override // Переопределяем метод loadUser ByUsername
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username) // Ищем пользователя по имени
                .orElseThrow(() -> new UsernameNotFoundException("User  not found with username: " + username)); // Если не найден, выбрасываем исключение
        return UserDetailsImpl.build(user); // Возвращаем объект UserDetails
    }
}
