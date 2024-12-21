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
                describe the image into following fileds only 
                Example of an image description given below, 
                Name: Chocolate Chip Cookies
                Ingredients: flour, butter, sugar, chocolate chips, eggs, vanilla extract, baking soda, salt
                Taste: Sweet, Chocolatey
                Cuisine: Western
                Preparation Time: 30 minutes
                Reviews: 4.8
            """
    
        response = model.generate_content([text, sample_file])

        description = response.candidates[0].content.parts[0].text

        return JSONResponse(content={"description": description})

    except Exception as e:
        return JSONResponse(status_code=500, content={"error": str(e)})

    finally:
        if os.path.exists(img_path):
            os.remove(img_path)

if __name__ == "__main__": 
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)