package com.documax.dot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PruebaController {

    @GetMapping("/dot")
    public String get() {
        return "index";
    }
}
