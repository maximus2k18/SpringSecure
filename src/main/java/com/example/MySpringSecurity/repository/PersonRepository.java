package com.example.MySpringSecurity.repository;

import com.example.MySpringSecurity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
    void deleteByEmail(String email);
    Person findByEmail (String email);
    List<Person> findAllByIdNot(Long id);
}
