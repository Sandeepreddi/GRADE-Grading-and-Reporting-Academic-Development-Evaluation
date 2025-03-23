import express from "express";
import bodyParser from "body-parser";
import mongoose from "mongoose";
import cors from "cors";
import dotenv from "dotenv";
import fs from "fs";
import natural from "natural";
import Groq from "groq-sdk";

import adminRoutes from "./routes/adminRoutes.js";
import studentRoutes from "./routes/studentRoutes.js";
import facultyRoutes from "./routes/facultyRoutes.js";
import { addDummyAdmin } from "./controller/adminController.js";

const app = express();
dotenv.config();

app.use(bodyParser.json({ limit: "30mb", extended: true }));
app.use(bodyParser.urlencoded({ limit: "30mb", extended: true }));
app.use(cors());

app.use("/api/admin", adminRoutes);
app.use("/api/faculty", facultyRoutes);
app.use("/api/student", studentRoutes);

const PORT = process.env.PORT || 5001;

// MongoDB Connection
mongoose
  .connect(process.env.CONNECTION_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => {
    addDummyAdmin();
    app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
  })
  .catch((error) => console.log("Mongo Error", error.message));

// AI Chatbot Configuration
const DATA_FILE = "colleges_data.json";
let cachedData = null;

function getCollegeData() {
  if (!cachedData) {
    try {
      cachedData = JSON.parse(fs.readFileSync(DATA_FILE, "utf-8"));
      console.log("College data loaded successfully.");
    } catch (error) {
      console.error("Error loading college data:", error);
      cachedData = {};
    }
  }
  return cachedData;
}

const groq = new Groq({ apiKey: process.env.GROQ_API_KEY });

function preprocess(text) {
  return text.toLowerCase().replace(/[^\w\s]/g, "");
}

function findBestMatch(userMessage, role, collegeData) {
  if (!collegeData[role]) return null;

  const tokenizer = new natural.WordTokenizer();
  const userTokens = tokenizer.tokenize(preprocess(userMessage));

  let bestMatch = { key: null, score: 0 };

  for (const key in collegeData[role]) {
    const keyTokens = tokenizer.tokenize(preprocess(key));
    const similarity = natural.JaroWinklerDistance(userTokens.join(" "), keyTokens.join(" "));

    if (similarity > bestMatch.score) {
      bestMatch = { key, score: similarity };
    }
  }

  return bestMatch.score > 0.75 ? collegeData[role][bestMatch.key] : null;
}

async function getAIResponse(userMessage) {
  try {
    console.log("Fetching AI response...");

    const chatCompletion = await groq.chat.completions.create({
      messages: [
        {
          role: "system",
          content: `http://localhost:3000/login/adminlogin`,
        },
        { role: "user", content: userMessage },
      ],
      model: "llama-3.3-70b-versatile",
    });

    return chatCompletion.choices?.[0]?.message?.content || "I couldn't generate a response.";
  } catch (error) {
    console.error("AI Model Error:", error);
    return "Sorry, I'm having trouble retrieving information right now.";
  }
}

app.post("/api/chat", async (req, res) => {
  try {
    const { message, role } = req.body;
    const collegeData = getCollegeData();

    let response = findBestMatch(message, role, collegeData);

    if (!response) {
      response = await getAIResponse(message);
    }

    res.json({ response });
  } catch (error) {
    console.error("Backend Error:", error);
    res.status(500).json({ error: "Internal Server Error" });
  }
});

// AI Dashboard Route
app.get("/api/open-ai-dashboard", (req, res) => {
  res.json({ message: "Open AIDashboard.js in src/components/ai/AIDashboard.js" });
});