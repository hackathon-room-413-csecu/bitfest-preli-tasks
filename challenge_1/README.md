# Banglish-to-Bengali Transliteration Model

This project aims to develop a model that converts Banglish text (Bengali written in English letters) into proper Bengali script. The model is trained on a dataset containing Banglish-to-Bengali pairs and fine-tuned to perform the transliteration task.

## Problem Statement

Iqbal's laptop was hacked by an evil hacker who uninstalled the Avro keyboard, leaving him unable to type in Bengali. He needs to write in Bengali for a Facebook comment debate but can't reinstall the keyboard. So, he decides to train a model to convert his Banglish text into Bengali script.

## Dataset

The dataset used for training and evaluation is from the Hugging Face dataset repository: [SKNahin/bengali-transliteration-data](https://huggingface.co/datasets/SKNahin/bengali-transliteration-data).

## Tasks

### 1. Load the Dataset
The dataset is loaded from the Hugging Face dataset repository, and it is split into training and validation subsets (e.g., 80/20 or 90/10 split).

### 2. Data Preprocessing
- Tokenize both Banglish and Bengali text using appropriate tokenization techniques for sequence-to-sequence tasks.
- Clean or filter the dataset to remove overly short or excessively long sentences.

### 3. Select a Model

For this task, we have chosen **mBART (Multilingual BART)**, a pre-trained sequence-to-sequence model available in Hugging Face's Transformers library.

#### Why mBART?

- **Multilingual Support**: mBART is trained on multiple languages, making it a strong candidate for tasks involving languages like Bengali. Since Banglish is essentially a variant of Bengali written in the English alphabet, mBART's multilingual capabilities allow it to handle such transliteration tasks effectively.
  
- **Translation & Text Generation**: mBART is specifically designed for sequence-to-sequence tasks such as machine translation and text generation. These tasks are aligned with our goal of converting Banglish (English-based text) to Bengali script, which involves generating text in a different script.

- **Performance on Low-Resource Languages**: mBART has shown strong performance for low-resource languages, which is crucial for Banglish-to-Bengali conversion since the dataset may not be as large as those for high-resource languages like English or French.

- **Pre-trained for Many Languages**: mBART is pre-trained on 50 languages and can handle multiple language pairs, including Bengali. This makes it an efficient and suitable choice for our task without the need for training a model from scratch.

Given these factors, mBART is an ideal choice for translating or generating Bengali text from Banglish.

### 4. Train the Model
The model is fine-tuned on the Banglish-to-Bengali dataset using the Hugging Face Trainer API. Key hyperparameters (learning rate, batch size, epochs) are adjusted to optimize the model for the task.

## Usage

Once the model is trained and saved, it can be used for converting new Banglish sentences into proper Bengali script.

### Example:
```python
from transformers import AutoModelForSeq2SeqLM, AutoTokenizer

# Load the pre-trained mBART model and tokenizer
model = AutoModelForSeq2SeqLM.from_pretrained('facebook/mbart-large-50-many-to-one-mmt')
tokenizer = AutoTokenizer.from_pretrained('facebook/mbart-large-50-many-to-one-mmt')

# Input Banglish text
banglish_text = "Amar naam Iqbal"

# Tokenize and generate Bengali translation
inputs = tokenizer(banglish_text, return_tensors="pt", padding=True, truncation=True)
outputs = model.generate(**inputs)

# Decode the generated text (Bengali script)
bengali_text = tokenizer.decode(outputs[0], skip_special_tokens=True)
print(bengali_text)
