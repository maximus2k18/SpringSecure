package com.example.MySpringSecurity.service;


import com.example.MySpringSecurity.model.Person;

import java.util.List;

public interface PersonService {
    public List<Person> getPersonList();
    Person savePerson(Person person);
    Person updatePerson(Person person);
    Person findByEmail(String email);
    void deletePerson(String email);
    long countPerson();
    List<Person> getAllPersonsExcept(Long senderId);
}
