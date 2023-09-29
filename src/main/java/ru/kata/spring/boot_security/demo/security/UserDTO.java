//package ru.kata.spring.boot_security.demo.security;
//
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import ru.kata.spring.boot_security.demo.models.User;
//
//import java.util.Collection;
//
//public class UserDTO implements UserDetails {
//
//    private final User user;
//
//    public UserDTO(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null; //возавращаем коолекцию прав пользоателя
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.user.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    //для получения данных аутентифицированного пользователя
//    public User getUser() {
//        return this.user;
//    }
//
//}
