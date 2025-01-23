package com.example.MySpringSecurity.controller;

import com.example.MySpringSecurity.model.UserForm;
import com.example.MySpringSecurity.repository.UserFormRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/submit")
public class WebController {
    private final UserFormRepository userFormRepository;

    public WebController(UserFormRepository userFormRepository) {
        this.userFormRepository = userFormRepository;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }
    @PostMapping
    public ResponseEntity<Void> handleForm(@Valid @RequestBody UserForm form) {
        // Проверка полей
        if (form.getName() == null || form.getName().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Возвращаем 400 Bad Request без текста
        }
        if (form.getDaytime() == null || form.getDaytime().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Возвращаем 400 Bad Request без текста
        }
        // Сохраняем данные в базу
        userFormRepository.save(form);
        // Возвращаем 200 OK
        return ResponseEntity.ok().build();
    }
}
