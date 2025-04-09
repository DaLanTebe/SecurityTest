package com.example.demo.security;

import com.example.demo.model.User; // Импортируем модель пользователя
import org.springframework.security.core.GrantedAuthority; // Импортируем интерфейс для представления полномочий
import org.springframework.security.core.userdetails.UserDetails; // Импортируем интерфейс UserDetails

import java.util.Collection; // Импортируем коллекции
import java.util.List; // Импортируем список
import java.util.Objects; // Импортируем класс для работы с объектами

public class UserDetailsImpl implements UserDetails {

    private Long id; // Идентификатор пользователя
    private String username; // Имя пользователя
    private String password; // Пароль пользователя
    private Collection<? extends GrantedAuthority> authorities; // Полномочия пользователя

    // Конструктор
    public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // Метод для создания UserDetailsImpl из User
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = List.of(); // Здесь можно добавить полномочия пользователя
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Возвращаем полномочия
    }

    @Override
    public String getPassword() {
        return password; // Возвращаем пароль
    }

    @Override
    public String getUsername() {
        return username; // Возвращаем имя пользователя
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Указываем, что аккаунт не истек
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Указываем, что аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Указываем, что учетные данные не истекли
    }

    @Override
    public boolean isEnabled() {
        return true; // Указываем, что аккаунт активен
    }

    // Переопределяем equals и hashCode для корректного сравнения
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Если ссылки одинаковые
        if (!(o instanceof UserDetailsImpl)) return false; // Если объект не является UserDetailsImpl
        UserDetailsImpl user = (UserDetailsImpl) o; // Приводим объект к UserDetailsImpl
        return Objects.equals(id, user.id); // Сравниваем идентификаторы
    }
}