package ru.kata.spring.boot_security.demo.models;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Not valid name!")
    @Size(min = 1, max = 50, message = "Enter correct value bet.1 and 15 chars!")
    private String username;

    private String password;
    private String useremail;
    private int userage;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles"
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
