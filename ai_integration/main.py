from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
import os
from io import BytesIO
import google.generativeai as genai
from dotenv import load_dotenv

load_dotenv()

genai.configure(api_key=os.getenv('GOOGLE_API_KEY'))

app = FastAPI()

@app.post('/recipe-from-image')
async def get_recipe_from_image(file: UploadFile = File(...)): 
    
    img_path = f"temp_{file.filename}"
    
    with open(img_path, "wb") as buffer:
        buffer.write(await file.read())
    
    try:
        sample_file = genai.upload_file(path=img_path)

        model = genai.GenerativeModel(model_name="models/gemini-1.5-flash-002")
        text = """
            You are given a recipe image, 
            do not generate unnecessary outputs, 
            omit ``` at the start and end, 
            omit usage of \" to represent "(")"
            Return the response in this exact JSON array format:
                {
                    "Name": "Recipe name here",
                    "Ingredients": ["ingredient1", "ingredient2", "ingredient3"],
                    "Taste": "Taste description here",
                    "Cuisine": "Cuisine type here",
                    "Preparation Time": "Preparation time here",
                    "Reviews": float
                }
        """
        
        response = model.generate_content([text, sample_file])

        description = response.candidates[0].content.parts[0].text

        return JSONResponse(description)

    except Exception as e:
        return JSONResponse(status_code=500, content={"error": str(e)})

    finally:
        if os.path.exists(img_path):
            os.remove(img_path)

if __name__ == "__main__": 
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)