package com.bitfest_preli.challenge_2.service;


import com.bitfest_preli.challenge_2.dto.IngredientRequestDTO;
import com.bitfest_preli.challenge_2.model.Ingredient;
import com.bitfest_preli.challenge_2.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
