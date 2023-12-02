package com.example.intellias.lib.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    private static final String HELLO_URL = "hello";

    @GetMapping("/hello")
    public String getHello(Model model){
        return HELLO_URL;
    }

}
