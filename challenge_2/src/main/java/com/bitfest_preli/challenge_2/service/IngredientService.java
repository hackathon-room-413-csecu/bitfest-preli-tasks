package com.bitfest_preli.challenge_2.service;


import com.bitfest_preli.challenge_2.dto.IngredientRequestDTO;
import com.bitfest_preli.challenge_2.dto.IngredientUpdateDTO;
import com.bitfest_preli.challenge_2.model.Ingredient;
import com.bitfest_preli.challenge_2.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public void addIngredient(IngredientRequestDTO ingredientRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequest.getName());
        ingredient.setQuantity(ingredientRequest.getQuantity());
        ingredient.setUnit(ingredientRequest.getUnit());
        ingredientRepository.save(ingredient);
    }

    public void addMultipleIngredients(List<IngredientRequestDTO> ingredientRequests) {
        for (IngredientRequestDTO ingredientRequest : ingredientRequests) {
            addIngredient(ingredientRequest); // Reusing the existing method to add individual ingredients
        }
    }

    public boolean updateIngredient(IngredientUpdateDTO ingredientUpdate) {
        Optional<Ingredient> existingIngredientOpt = ingredientRepository.findByName(ingredientUpdate.getName());

        if (existingIngredientOpt.isPresent()) {
            Ingredient existingIngredient = existingIngredientOpt.get();
            existingIngredient.setQuantity(ingredientUpdate.getQuantity());
            existingIngredient.setUnit(ingredientUpdate.getUnit());
            ingredientRepository.save(existingIngredient);
            return true;
        }
        return false; // Ingredient not found
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> getAvailableIngredients() {
        return ingredientRepository.findByQuantityGreaterThan(0);
    }

    @Transactional
    public void deleteIngredientByName(String name) {
        // Check if ingredient with the given name exists
        Optional<Ingredient> ingredient = ingredientRepository.findByName(name);
        if (ingredient.isPresent()) {
            ingredientRepository.delete(ingredient.get());
        } else {
            throw new RuntimeException("Ingredient not found with name: " + name);
        }
    }
}
