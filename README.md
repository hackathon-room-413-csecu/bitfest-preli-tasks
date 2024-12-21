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

