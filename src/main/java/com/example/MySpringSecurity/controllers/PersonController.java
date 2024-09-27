package com.example.MySpringSecurity.controllers;

import com.example.MySpringSecurity.model.Person;
import com.example.MySpringSecurity.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonService service;

    @GetMapping
    public List<Person> getPersonList(){
        return service.getPersonList();
    }

    @PostMapping("save")
    public String savePerson(@RequestBody Person person){
        service.savePerson(person);
        return "Person successfully saved";
    }

    @GetMapping("/{email}")
    public Person findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/count")
    public long getCount() {
        return service.countPerson();
    }

    @PutMapping("update_person")
    public Person updatePerson(@RequestBody Person person) {
        return service.updatePerson(person);
    }

    @DeleteMapping("delete_person/{email}")
    public void deletePerson(@PathVariable String email) {
        service.deletePerson(email);
    }
}
