package com.bitfest_preli.challenge_2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientsController {
    @GetMapping("/hello")
    public ResponseEntity<?> getThings() {
        return ResponseEntity.ok("Hello world");
    }
}
