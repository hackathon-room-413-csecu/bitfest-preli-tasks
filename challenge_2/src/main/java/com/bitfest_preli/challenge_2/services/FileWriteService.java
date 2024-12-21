package com.bitfest_preli.challenge_2.services;

import com.bitfest_preli.challenge_2.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileWriteService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String FILE_PATH = "my_fav_recipes.txt";

    public void writeRecipeToFile(Recipe recipe) {
        try {
            // Ensure file exists before appending
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                Files.createFile(path); // Create the file if it does not exist
            }

            // Append the recipe to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.newLine();
                writer.newLine();
                writer.write("---");
                writer.newLine();
                writer.write(formatRecipe(recipe));
//                writer.newLine();
//                writer.write("---");
//                writer.newLine();
            }

            logger.info("Recipe successfully written to file: " + FILE_PATH);
        } catch (IOException e) {
            logger.error("Error writing recipe to file: " + e.getMessage());
        }
    }

    private String formatRecipe(Recipe recipe) {
        return "Name: " + recipe.getName() + "\n" +
                "Ingredients: " + String.join(", ", recipe.getIngredients()) + "\n" +
                "Taste: " + recipe.getTaste() + "\n" +
                "Cuisine: " + recipe.getCuisine() + "\n" +
                "Preparation Time: " + recipe.getPreparationTime() + "\n" +
                "Reviews: " + recipe.getReviews();
    }
}
