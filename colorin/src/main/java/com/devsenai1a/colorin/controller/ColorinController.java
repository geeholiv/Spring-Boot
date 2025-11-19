package com.devsenai1a.colorin.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*") 
public class ColorinController {

    private String emailCorreto = "anageisa@gmail.com";
    private String senhaCorreta = "123456";

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> dados) {

        String email = dados.get("email");
        String senha = dados.get("senha");

        if (emailCorreto.equals(email) && senhaCorreta.equals(senha)) {
            return ResponseEntity.ok("Login correto!");
        } else {
            return ResponseEntity.status(401).body("Login inválido!");
        }
    }
}

