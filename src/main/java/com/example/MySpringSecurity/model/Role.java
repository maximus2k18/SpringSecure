package com.example.MySpringSecurity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    // Название роли, например "ADMIN" или "USER"
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons;
}
