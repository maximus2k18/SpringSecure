package com.example.MySpringSecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Data
@Entity
@Table(name = "new_persones")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    @Pattern(regexp = "^[а-яА-Я]+$", message = "LastName can only contain letters")
    private String lastName;

    @Pattern(regexp = "^[а-яА-Я]+$", message = "FirstName can only contain letters")
    private String firstName;

    @Column(unique = true)
    private String email;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z0-9!()?:%]+$" , message = "Пароль должен содержать только буквы, цифры и символы !()?:%")
    private String password;


    private LocalDate dateOfBirth;

    @Transient
    private int age;

    @ManyToMany
    @JoinTable(
            name = "person_roles", // Имя таблицы для связи
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
