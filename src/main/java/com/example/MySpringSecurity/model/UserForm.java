package com.example.MySpringSecurity.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "person_table4", schema = "public", catalog = "postrges")
public class UserForm {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
     long id;
     @Column(nullable = false)
     String name;
     @Column(nullable = false)
     String color;
     @Column(name = "is_adult", nullable = false) // nullable = false обязательно
     Boolean isAdult;
     @Column(nullable = false)
     String daytime;
}
