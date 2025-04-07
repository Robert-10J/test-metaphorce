package com.conferencias.conferencias_metaphorce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public Map<String, Object> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("hiii", "kpex");
        return response;
    }
}
