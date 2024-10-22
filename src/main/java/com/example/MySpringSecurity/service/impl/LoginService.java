package com.example.MySpringSecurity.service.impl;

import com.example.MySpringSecurity.model.Person;
import com.example.MySpringSecurity.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private PersonService personService;

    public boolean authenticate(String email, String password) {
        Person person = personService.findByEmail(email);
        if (person == null) {
            return false; // Пользователь с таким email не найден
        }
        // Проверка пароля (например, сравнение хешей)
        return person.getPassword().equals(password);  //todo Здесь нужно использовать шифрование
    }
}
