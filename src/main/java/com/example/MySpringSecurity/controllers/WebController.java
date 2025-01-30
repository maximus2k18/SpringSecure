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

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

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
            @RequestParam("action") String action,
            @ModelAttribute("person") @Valid Person person,
            BindingResult result, Model model) {

        if (action.equals("login")) {
            return "redirect:/login"; // Перенаправляем на страницу входа
        }
        if (result.hasErrors()) {
            model.addAttribute("dob",dob);
            return "register"; //вернуться на страницу, если есть ошибки
        }
        // Парсинг даты
        if (!parseDateOfBirth(dob, person, model)) {
            return "register"; // Возвращаемся с сообщением об ошибке
        }

        // Сохранение пользователя
        try {
            personService.savePerson(person);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

            if (errorMessage.contains("email")) {
                model.addAttribute("emailError", "Упс, такая почта уже используется!");
            } else if (errorMessage.contains("user_name")) {
                model.addAttribute("usernameError", "Это имя пользователя уже занято!");
            }
            model.addAttribute("dob", dob); // Сохранение введенной даты
            return "register"; // Возвращаемся с сообщением об ошибке
        }
        return "redirect:/home";
    }

    private boolean parseDateOfBirth(String dob, Person person, Model model) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            person.setDateOfBirth(LocalDate.parse(dob, formatter));
            return true;
        } catch (DateTimeParseException e) {
            model.addAttribute("dateError", "Неверный формат даты: " + dob);
            return false;
        }
    }
}
