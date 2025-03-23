//const axios = require("axios");
import axios from "axios";
import * as cheerio from "cheerio";

import fs from "fs";
//const cheerio = require("cheerio");
//const fs = require("fs");

const COLLEGE_URLS = [
  "https://mlrit.ac.in/",
  "https://mlrit.ac.in/about-us/",

  //branches
  "https://mlrit.ac.in/aeronautical-engineering/",
  "https://mlrit.ac.in/computer-science-engineering/",
  "https://mlrit.ac.in/cse-ds/",
  "https://mlrit.ac.in/cse-cs/",
  "https://mlrit.ac.in/ece/",
  "https://mlrit.ac.in/it/",
  "https://mlrit.ac.in/mechanical-engineering/",
  "https://mlrit.ac.in/ai-ml/",
  "https://mlrit.ac.in/csit/",
  "https://mlrit.ac.in/cse-aiml/",
  "https://mlrit.ac.in/cse-aiml/",
  "https://mlrit.ac.in/freshman/",
  "https://mlrit.ac.in/mba/",

  "https://mlrit.ac.in/admissions/",
  "https://mlrit.ac.in/examinations/",


  "https://mlrit.ac.in/placements/placements-statistics-2024/",
  "https://mlrit.ac.in/placements/placements-statistics-2023/",

  //innovatioon cell
  "https://mlrit.ac.in/innovation-cell/innovation-overview/",
  "https://mlrit.ac.in/innovation-cell/student-startup/",
  "https://mlrit.ac.in/innovation-cell/enterpreneurship-courses/",
  "https://mlrit.ac.in/innovation-cell/infrastructure/",
  "https://mlrit.ac.in/innovation-cell/incubation-services/",
  "https://mlrit.ac.in/innovation-cell/innovation-events/",


  "https://mlrit.ac.in/campus-life/",
  "https://mlrit.ac.in/sports/",
  "https://mlrit.ac.in/research/",
  "https://mlrit.ac.in/iqac/",
  "https://naac.mlrit.ac.in/?_gl=1*12ta1k4*_ga*Mjc2MTk0NDg4LjE3MzkwMzg3NTU.*_ga_3GME98B535*MTczOTI2OTM2Ni40LjEuMTczOTI2OTc3My4yMi4wLjA.*_gcl_au*MTUwMTY2Mzc4Ny4xNzM5MDM4NzU1",
  "https://mlrit.ac.in/events/",
  "https://mlrit.ac.in/contactus/",
  "https://mlrit.ac.in/careers/",



  "https://mlrit.ac.in/faculty/dr-n-v-rajasekhar-reddy/",


  "https://en.wikipedia.org/wiki/Marri_Rajasekhar_Reddy",
  



];

// Function to fetch and clean webpage text
const fetchText = async (url) => {
  try {
    console.log(`Scraping: ${url}`);
    const { data } = await axios.get(url);
    const $ = cheerio.load(data);

    // Extract text from all paragraphs
    const content = $("p, h1, h2, h3, h4, h5, h6, li")
      .map((_, el) => $(el).text().trim())
      .get()
      .filter(text => text.length > 30) // Ignore very short texts
      .slice(0, 10) // Limit to first 10 sections for brevity
      .join("\n\n");

    return { url, content };
  } catch (error) {
    console.error(`Error scraping ${url}:`, error.message);
    return { url, content: "Failed to retrieve data" };
  }
};

// Scrape all college pages and save to JSON
async function scrapeAndSave() {
  const results = await Promise.all(COLLEGE_URLS.map(fetchText));

  // Save to JSON file
  fs.writeFileSync("colleges_data.json", JSON.stringify(results, null, 2));
  console.log("Scraping complete! Data saved to colleges_data.json");
}

// Run the scraper
scrapeAndSave();
