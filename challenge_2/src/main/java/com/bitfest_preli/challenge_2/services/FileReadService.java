package com.bitfest_preli.challenge_2.services;

import com.bitfest_preli.challenge_2.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileReadService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Recipe> readFile(String fileName) {
        List<Recipe> recipes = new ArrayList<>();
        File file = new File(fileName); // Load the file from the root directory

        if (!file.exists()) {
            logger.error("File not found: " + fileName);
            return recipes; // Return an empty list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Recipe recipe = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    if (recipe != null) {
                        recipes.add(recipe);
                    }
                    recipe = new Recipe();
                    recipe.setName(line.split(": ", 2)[1].trim());
                } else if (line.startsWith("Ingredients:")) {
                    if (recipe != null) {
                        recipe.setIngredients(Arrays.asList(line.split(": ", 2)[1].trim().split(", ")));
                    }
                } else if (line.startsWith("Taste:")) {
                    if (recipe != null) {
                        recipe.setTaste(line.split(": ", 2)[1].trim());
                    }
                } else if (line.startsWith("Cuisine:")) {
                    if (recipe != null) {
                        recipe.setCuisine(line.split(": ", 2)[1].trim());
                    }
                } else if (line.startsWith("Preparation Time:")) {
                    if (recipe != null) {
                        recipe.setPreparationTime(line.split(": ", 2)[1].trim());
                    }
                } else if (line.startsWith("Reviews:")) {
                    if (recipe != null) {
                        recipe.setReviews(Double.parseDouble(line.split(": ", 2)[1].trim()));
                    }
                } else if (line.trim().isEmpty() && recipe != null) {
                    recipes.add(recipe);
                    recipe = null;
                }
            }

            // Add the last recipe if it exists
            if (recipe != null) {
                recipes.add(recipe);
            }

        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
        }

        return recipes;
    }
}
