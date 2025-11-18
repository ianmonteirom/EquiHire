package br.com.equihire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/equihire"})
    public String home() {
        return "redirect:/candidatos";
    }
}
