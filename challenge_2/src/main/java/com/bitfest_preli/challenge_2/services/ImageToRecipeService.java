package com.bitfest_preli.challenge_2.services;

import com.bitfest_preli.challenge_2.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageToRecipeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String FASTAPI_URL = "http://0.0.0.0:8000/recipe-from-image";

    @Autowired
    private RestTemplate restTemplate;

    public List<Recipe> getRecipesFromImages(String directoryPath, List<String> fileNames) throws IOException {
        List<Recipe> recipes = new ArrayList<>();

        for (String fileName : fileNames) {
            File imageFile = new File(directoryPath + "/" + fileName);

            Recipe recipe = sendImageToFastAPI(imageFile);

            if (recipe != null) {
                recipes.add(recipe);
            }
        }

        return recipes;
    }

    private Recipe sendImageToFastAPI(File imageFile) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", imageFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    FASTAPI_URL, HttpMethod.POST, requestEntity, String.class);

            return parseRecipeResponse(response.getBody());

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private Recipe parseRecipeResponse(String responseBody) {
        try {
            // Jackson ObjectMapper (add the dependency in pom.xml if not added yet)
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return objectMapper.readValue(responseBody, Recipe.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
