# Ingredients Management API Documentation

This document provides the details for the available API endpoints to manage ingredients.

---

### 1. Route: `/ingredients/hello`
**Method**: GET  
**Sample Response**:
```json
{
  "message": "Hello world"
}
```
Description: This is a test endpoint to verify the API is working

### 2. Route: /ingredients/add
Method: POST
Sample Payload:
```json
{
  "name": "Sugar",
  "quantity": 2,
  "unit": "kg",
}

```
Description: Adds a new ingredient to the database.
### 3. Route: /ingredients/add/multiple
Method: POST
Sample Payload:
```json
[
  {
    "name": "Sugar",
    "quantity": 2,
    "unit": "kg",
  },
  {
    "name": "Salt",
    "quantity": 1,
    "unit": "kg",
  }
]
```
### 4. Route: /ingredients/update
Method: PUT
Sample Payload:
```json
{
  "name": "Sugar",
  "quantity": 5,
  "unit": "kg",
  "lastUpdated": "2024-12-23"
}
```
Description: Updates an existing ingredient’s details (only the ingredient name is required).

### 5. Route: /ingredients/all
Method: GET
Sample Response:
```json
[
  {
    "id": 1,
    "name": "Sugar",
    "quantity": 2,
    "unit": "kg",
    "lastUpdated": "2024-12-23"
  },
  {
    "id": 2,
    "name": "Salt",
    "quantity": 1,
    "unit": "kg",
    "lastUpdated": "2024-12-23"
  }
]
```
Description: Retrieves all ingredients from the database.

### 6. Route: /ingredients/available
Method: GET
Sample Response:
```json
[
  {
    "id": 1,
    "name": "Sugar",
    "quantity": 2,
    "unit": "kg",
    "lastUpdated": "2024-12-23"
  }
]
```
Description: Retrieves all ingredients that are currently available (quantity > 0).

### 7. Route: /ingredients/delete
Method: DELETE
Sample Payload:
```json
"Sugar"
```
Description: Deletes an ingredient by its name.


### Route: 8. `/get-recipes`
**Method**: `GET`

**Description**:  
This endpoint retrieves a list of recipes from a file and returns it in JSON format.

#### Sample Response:
```json
[
    {
        "name": "Spaghetti Bolognese",
        "ingredients": ["Spaghetti", "Ground beef", "Tomato sauce", "Garlic", "Onions"],
        "taste": "Savory and hearty",
        "cuisine": "Italian",
        "preparationTime": "30 minutes",
        "reviews": 4.5
    },
    {
        "name": "Chicken Curry",
        "ingredients": ["Chicken", "Curry powder", "Coconut milk", "Rice", "Garlic", "Onions"],
        "taste": "Spicy and creamy",
        "cuisine": "Indian",
        "preparationTime": "45 minutes",
        "reviews": 4.7
    }
]

```

### 9. Route: `/add-recipe-text`
**Method**: `POST`

**Description**:  
This endpoint adds a new recipe to the file `my_fav_recipes.txt`. The recipe details are provided in the request body.

#### Sample Payload:
```json
{
    "name": "Spaghetti Bolognese",
    "ingredients": ["Spaghetti", "Ground beef", "Tomato sauce", "Garlic", "Onions"],
    "taste": "Savory and hearty",
    "cuisine": "Italian",
    "preparationTime": "30 minutes",
    "reviews": 4.5
}

```



### 10. Route: `/add-recipe-from-directory`
**Method**: `POST`

**Description**:  
This endpoint adds recipes from multiple text files located in the specified directory. It processes the list of filenames provided in the request body.

#### Sample Payload:
```json
[
    "recipe1.txt",
    "recipe2.txt",
    "recipe3.txt"
]
```

#### Sample Response:
```json
{
    "message": "Recipes successfully added to my_fav_recipes.txt"
}
```

#### Response Status Codes:
- **201 Created**: Successfully added the recipes from the directory.
- **204 No Content**: No recipes found in the specified files.

#### Example Request:
```bash
POST http://localhost:8080/add-recipe-from-directory
```

---

### 11. Route: `/get-recipe-from-image`
**Method**: `POST`

**Description**:  
This endpoint processes images to extract recipe information. It processes the list of image filenames provided in the request body and retrieves the recipes.

#### Sample Payload:
```json
[
    "image1.jpg",
    "image2.jpg",
    "image3.jpg"
]
```

#### Sample Response:
```json
{
    "message": "Recipes successfully added to my_fav_recipes.txt"
}
```

#### Response Status Codes:
- **201 Created**: Successfully added the recipes from the images.
- **204 No Content**: No recipes found in the specified files.

#### Example Request:
```bash
POST http://localhost:8080/get-recipe-from-image
```

---

### Notes:
- **For `/add-recipe-text`**: The request body should contain a single recipe object to be added to the file.
- **For `/add-recipe-from-directory`**: The request body should contain a list of filenames of text files containing recipes.
- **For `/get-recipe-from-image`**: The request body should contain a list of image filenames to be processed for recipe extraction.
```

