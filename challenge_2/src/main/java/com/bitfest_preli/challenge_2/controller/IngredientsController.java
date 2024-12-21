package com.bitfest_preli.challenge_2.controller;

import com.bitfest_preli.challenge_2.dto.IngredientRequestDTO;
import com.bitfest_preli.challenge_2.dto.IngredientUpdateDTO;
import com.bitfest_preli.challenge_2.model.Ingredient;
import com.bitfest_preli.challenge_2.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> getThings() {
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewIngredient(@RequestBody IngredientRequestDTO ingredientRequest) {
        try {
            ingredientService.addIngredient(ingredientRequest);
            return ResponseEntity.ok("Ingredient added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add ingredient: " + e.getMessage());
        }
    }

    @PostMapping("/add/multiple")
    public ResponseEntity<?> addMultipleIngredients(@RequestBody List<IngredientRequestDTO> ingredientRequests) {
        try {
            ingredientService.addMultipleIngredients(ingredientRequests);
            return ResponseEntity.ok("Ingredients added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add ingredients: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateIngredient(@RequestBody IngredientUpdateDTO ingredientUpdate) {
        try {
            boolean updated = ingredientService.updateIngredient(ingredientUpdate);
            if (updated) {
                return ResponseEntity.ok("Ingredient updated successfully.");
            } else {
                return ResponseEntity.status(404).body("Ingredient not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update ingredient: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Ingredient>> getAvailableIngredients() {
        List<Ingredient> ingredients = ingredientService.getAvailableIngredients();
        return ResponseEntity.ok(ingredients);
    }
}
