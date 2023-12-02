package com.example.intellias.lib.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutoritheController {
    @GetMapping("/login")
    public String appLoginPage(Model model){
        return "/login/login";
    }

}
