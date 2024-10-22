package com.example.MySpringSecurity.controllers;

import com.example.MySpringSecurity.model.Person;
import com.example.MySpringSecurity.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
public class WebController {

    @GetMapping("/myPage")
    public String showMyPage() {
        return "myPage";
    }

    @Autowired
    private PersonService personService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /*@ModelAttribute*/
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/register")
    public String registerPerson(
            @RequestParam("dob") String dob,
            @ModelAttribute("person") @Valid Person person,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("dob",dob);
            return "register"; //вернуться на страницу, если есть ошибки
        }
        // Собираем дату из отдельных частей
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate dateOfBirth = LocalDate.parse(dob, formatter);
            person.setDateOfBirth(dateOfBirth);
        }
        catch (DateTimeParseException e){
            model.addAttribute("dateError", "Неверный формат даты: " + dob);
            return "register"; // Вернуться на страницу с сообщением об ошибке
        }

        try {
            personService.savePerson(person);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("email")){
                model.addAttribute("emailError", "Упс, такая почта уже используется!");
            }
            if (e.getMessage().contains("username")){
                model.addAttribute("usernameError", "Это имя пользователя уже занято!");
            }
            model.addAttribute("dob", dob); // Чтобы сохранить введенную дату
            return "register"; // Вернуться на страницу с сообщением об ошибке
        }
        return "redirect:/home";
    }
}
