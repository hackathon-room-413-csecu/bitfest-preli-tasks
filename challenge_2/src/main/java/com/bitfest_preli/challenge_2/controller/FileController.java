package com.bitfest_preli.challenge_2.controller;

import com.bitfest_preli.challenge_2.model.Recipe;
import com.bitfest_preli.challenge_2.services.FileReadService;
import com.bitfest_preli.challenge_2.services.FileWriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileController {

    private final FileReadService fileReadService;
    private final FileWriteService fileWriteService;

    public FileController(FileReadService fileReadService, FileWriteService fileWriteService) {
        this.fileReadService = fileReadService;
        this.fileWriteService = fileWriteService;
    }

    @GetMapping("/get-recipes")
    public ResponseEntity<List<Recipe>> getRecipes() {
        String fileName = "my_fav_recipes.txt";
        return new ResponseEntity<List<Recipe>>(
                fileReadService.readFile(fileName),
                HttpStatus.OK
        );
    }

    @PostMapping("/add-recipe-text")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        fileWriteService.writeRecipeToFile(recipe);
        return new ResponseEntity<String>("Recipe added successfully", HttpStatus.CREATED);
    }
}
