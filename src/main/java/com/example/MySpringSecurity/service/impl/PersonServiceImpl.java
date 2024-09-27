package com.example.MySpringSecurity.service.impl;

import com.example.MySpringSecurity.model.Person;
import com.example.MySpringSecurity.repository.PersonRepository;
import com.example.MySpringSecurity.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;

    @Override
    public List<Person> getPersonList() {
        return repository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return repository.save(person);
    }

    @Override
    @Transactional
    public void deletePerson(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public Person findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public long countPerson() {
        return repository.count();
    }

    @Override
    public List<Person> getAllPersonsExcept(Long senderId) {
        return repository.findAllByIdNot(senderId);
    }
}
