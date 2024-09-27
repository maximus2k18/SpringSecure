package com.example.MySpringSecurity.controllers;

import com.example.MySpringSecurity.model.Person;
import com.example.MySpringSecurity.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class WebController {

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
            @RequestParam("dob_day") int day,
            @RequestParam("dob_month") int month,
            @RequestParam("dob_year") int year,
            @ModelAttribute("person") @Valid Person person,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dob_day", day);
            model.addAttribute("dob_month", month);
            model.addAttribute("dob_year", year);
            return "register"; //вернуться на страницу, если есть ошибки
        }
        // Собираем дату из отдельных частей
        try {
            person.setDateOfBirth(LocalDate.of(year, month, day));
        }
        catch (Exception e){
            model.addAttribute("dateError", "Неверная дата: " + day + "/" + month + "/" + year);
            return "register"; // Вернуться на страницу с сообщением об ошибке
        }
        // Сохранение пользователя в бд
        personService.savePerson(person);
        return "redirect:/home";
    }
}
