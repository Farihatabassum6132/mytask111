const inputText = document.getElementById('inputText');
const outputText = document.getElementById('outputText');
const sourceLang = document.getElementById('sourceLang');
const targetLang = document.getElementById('targetLang');
const translateBtn = document.getElementById('translateBtn');
const copyBtn = document.getElementById('copyBtn');
const speechBtn = document.getElementById('speechBtn');

// 1. API Call and Translation Logic
translateBtn.addEventListener('click', () => {
    let text = inputText.value.trim();
    if (!text) {
        alert("Please enter some text first!");
        return;
    }

    outputText.placeholder = "Translating...";
    
    let from = sourceLang.value;
    let to = targetLang.value;
    let apiUrl = `https://api.mymemory.translated.net/get?q=${encodeURIComponent(text)}&langpair=${from}|${to}`;

    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            outputText.value = data.responseData.translatedText;
        })
        .catch(() => {
            alert("Error in fetching translation!");
            outputText.placeholder = "Translation failed.";
        });
});

// 2. Optional Feature: Copy Button
copyBtn.addEventListener('click', () => {
    if (outputText.value) {
        navigator.clipboard.writeText(outputText.value);
        alert("Translated text copied to clipboard!");
    }
});

// 3. Optional Feature: Text-to-Speech (Listen)
speechBtn.addEventListener('click', () => {
    if (outputText.value) {
        let utterance = new SpeechSynthesisUtterance(outputText.value);
        utterance.lang = targetLang.value;
        speechSynthesis.speak(utterance);
    }
});