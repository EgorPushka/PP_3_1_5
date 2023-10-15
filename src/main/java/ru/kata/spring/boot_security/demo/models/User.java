package ru.kata.spring.boot_security.demo.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Not valid name!")
    @Size(min = 1, max = 25, message = "Enter correct value bet.1 and 25 chars!")
    private String username;

    @NotEmpty(message = "Enter correct value bet.1 and 25 chars!")
    @Size(min = 2, max = 25)
    private String lastname;

    @NotEmpty(message = "Enter correct value > 2 chars!")
    @Size(min = 2)
    private String password;

    @Email
    private String useremail;

    @Min(value = 0, message = "Enter correct value  > 0")
    private int userage;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles"
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String username, String password, String useremail, int userage, String lastname) {
        this.username = username;
        this.password = password;
        this.useremail = useremail;
        this.userage = userage;
        this.lastname = lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}
