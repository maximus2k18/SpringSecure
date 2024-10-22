package com.example.MySpringSecurity.controllers;

import com.example.MySpringSecurity.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String loginPerson(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        boolean isAuthenticated = loginService.authenticate(email, password);
        if (!isAuthenticated) {
            model.addAttribute("loginError", "Неверный email или пароль");
            return "login"; // Вернуться на страницу входа с ошибкой
        }
        return "redirect:/home"; // Успешный вход
    }
}
