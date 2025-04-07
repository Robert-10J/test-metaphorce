package com.conferencias.conferencias_metaphorce.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

 //puerto de postgresql = 49977

  @GetMapping("/user")
  public Map<String, Object> index() {
    Map<String, Object> response = new HashMap<>();
    response.put("hiii", "kpex");
    return response;
  }
}
